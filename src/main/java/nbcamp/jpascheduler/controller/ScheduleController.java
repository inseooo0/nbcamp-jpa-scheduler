package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleResponseDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
import nbcamp.jpascheduler.dto.UserResponseDto;
import nbcamp.jpascheduler.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    @PostMapping
    ScheduleResponseDto saveSchedule(@RequestBody ScheduleCreateDto requestDto) {
        Schedule schedule = scheduleService.save(requestDto);
        List<User> users = schedule.findUsers();

        ScheduleResponseDto responseDto = modelMapper.map(schedule, ScheduleResponseDto.class);
        for (User user : users) {
            responseDto.getUsers().add(modelMapper.map(user, UserResponseDto.class));
        }
        return responseDto;
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        List<User> users = schedule.findUsers();

        ScheduleResponseDto responseDto = modelMapper.map(schedule, ScheduleResponseDto.class);
        for (User user : users) {
            responseDto.getUsers().add(modelMapper.map(user, UserResponseDto.class));
        }
        return responseDto;
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedule(@RequestParam("pageNum") int pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<Schedule> scheduleList = scheduleService.findAll(pageNum, pageSize);
        List<ScheduleResponseDto> dtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            List<User> users = schedule.findUsers();

            ScheduleResponseDto responseDto = modelMapper.map(schedule, ScheduleResponseDto.class);
            for (User user : users) {
                responseDto.getUsers().add(modelMapper.map(user, UserResponseDto.class));
            }
            dtoList.add(responseDto);
        }
        return dtoList;
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId,
                                              @RequestBody ScheduleUpdateDto requestDto) {
        Schedule schedule = scheduleService.update(scheduleId, requestDto);
        List<User> users = schedule.findUsers();

        ScheduleResponseDto responseDto = modelMapper.map(schedule, ScheduleResponseDto.class);
        for (User user : users) {
            responseDto.getUsers().add(modelMapper.map(user, UserResponseDto.class));
        }
        return responseDto;
    }

    @DeleteMapping("/{scheduleId}")
    public String removeSchedule(@PathVariable Long scheduleId) {
        scheduleService.removeById(scheduleId);
        return "ok";
    }
}
