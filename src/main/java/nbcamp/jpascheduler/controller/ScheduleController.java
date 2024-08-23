package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.domain.User;
import nbcamp.jpascheduler.dto.*;
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
    public List<ScheduleResponseDtoVer2> getAllSchedule(@RequestParam("pageNum") int pageNum,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<Schedule> scheduleList = scheduleService.findAll(pageNum, pageSize);
        List<ScheduleResponseDtoVer2> dtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            dtoList.add(modelMapper.map(schedule, ScheduleResponseDtoVer2.class));
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
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.removeById(scheduleId);
        return "ok";
    }
}
