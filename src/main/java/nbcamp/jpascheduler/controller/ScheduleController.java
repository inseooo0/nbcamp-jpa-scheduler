package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.*;
import nbcamp.jpascheduler.annotation.AdminAuthenticationMethod;
import nbcamp.jpascheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    ScheduleResponseDto saveSchedule(@RequestBody ScheduleCreateDto requestDto) {
        Schedule schedule = scheduleService.save(requestDto);
        return ScheduleResponseDto.of(schedule);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        return ScheduleResponseDto.of(schedule);
    }

    @GetMapping
    public List<ScheduleResponseDtoVer2> getAllSchedule(@RequestParam("pageNum") int pageNum,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<Schedule> scheduleList = scheduleService.findAll(pageNum, pageSize);
        List<ScheduleResponseDtoVer2> dtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            dtoList.add(ScheduleResponseDtoVer2.of(schedule));
        }
        return dtoList;
    }

    @AdminAuthenticationMethod
    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId,
                                              @RequestBody ScheduleUpdateDto requestDto) {
        Schedule schedule = scheduleService.update(scheduleId, requestDto);

        return ScheduleResponseDto.of(schedule);
    }

    @AdminAuthenticationMethod
    @DeleteMapping("/{scheduleId}")
    public String deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.removeById(scheduleId);
        return "ok";
    }
}
