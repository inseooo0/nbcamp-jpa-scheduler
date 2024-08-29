package nbcamp.jpascheduler.service;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.ScheduleManagement;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
import nbcamp.jpascheduler.exception.ApiException;
import nbcamp.jpascheduler.exception.CommonErrorCode;
import nbcamp.jpascheduler.repository.ScheduleRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository repository;
    private final UserService userService;
    private final ScheduleManagementService scheduleManagementService;
    private final RestTemplate restTemplate;

    @Transactional
    public Schedule save(ScheduleCreateDto dto) {
        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent());
        List<Long> userIds = dto.getUserIds();
        for (Long userId : userIds) {
            User user = userService.findById(userId);
            if (user == null) {
                throw new ApiException(CommonErrorCode.INVALID_PARAMETER);
            }
            schedule.getManagementList().add(scheduleManagementService.save(schedule, user));
        }

        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .build().toUri();

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class); //get method 로 uri 요청
        String responseString = responseEntity.getBody();
        JSONArray jsonArray = new JSONArray(responseString);

        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            String dateString = (String) jsonObject.get("date");
            String[] split = dateString.split("-");
            LocalDate now = LocalDate.now();

            if (split[0].equals(now.format(DateTimeFormatter.ofPattern("MM")))
                    && split[1].equals(now.format(DateTimeFormatter.ofPattern("dd")))) {
                schedule.setWeather((String) jsonObject.get("weather"));
                break;
            }
        }
        return repository.save(schedule);
    }

    public Schedule findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ApiException(CommonErrorCode.INVALID_PARAMETER));
    }

    public List<Schedule> findAll(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("updateAt").descending());
        return repository.findAll(pageRequest).getContent();
    }

    @Transactional
    public Schedule update(Long id, ScheduleUpdateDto dto) {
        Schedule schedule = findById(id);
        schedule.update(dto.getTitle(), dto.getContent());
        List<Long> userIds = dto.getUserIds();
        List<ScheduleManagement> currentManagements = schedule.getManagementList();
        List<Long> currentUserIds = schedule.findUserIds();
        List<ScheduleManagement> removeManagements = new ArrayList<>();

        for (ScheduleManagement sm : currentManagements) {
            if (!userIds.contains(sm.getUser().getId())) {
                scheduleManagementService.removeById(sm.getId());
                removeManagements.add(sm);
            }
        }

        for (Long userId : userIds) {
            if (!currentUserIds.contains(userId)) {
                User user = userService.findById(userId);
                ScheduleManagement newManagement = scheduleManagementService.save(schedule, user);
                currentManagements.add(newManagement);
            }
        }
        currentManagements.removeAll(removeManagements);
        return schedule;
    }

    @Transactional
    public void removeById(Long id) {
        repository.deleteById(id);
    }
}
