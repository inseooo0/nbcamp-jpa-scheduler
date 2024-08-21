package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleResponseDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
import nbcamp.jpascheduler.service.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    @PostMapping
    ScheduleResponseDto saveSchedule(@RequestBody ScheduleCreateDto requestDto) {
        Schedule schedule = scheduleService.save(requestDto);
        return modelMapper.map(schedule, ScheduleResponseDto.class);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        Schedule findSchedule = scheduleService.findById(scheduleId);
        return modelMapper.map(findSchedule, ScheduleResponseDto.class);
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId,
                                              @RequestBody ScheduleUpdateDto requestDto) {
        Schedule updated = scheduleService.update(scheduleId, requestDto);
        return modelMapper.map(updated, ScheduleResponseDto.class);
    }
}
