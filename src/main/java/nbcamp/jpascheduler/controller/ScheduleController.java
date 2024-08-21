package nbcamp.jpascheduler.controller;

import lombok.RequiredArgsConstructor;
import nbcamp.jpascheduler.domain.Schedule;
import nbcamp.jpascheduler.dto.ScheduleCreateDto;
import nbcamp.jpascheduler.dto.ScheduleResponseDto;
import nbcamp.jpascheduler.dto.ScheduleUpdateDto;
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
        return modelMapper.map(schedule, ScheduleResponseDto.class);
    }

    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        Schedule findSchedule = scheduleService.findById(scheduleId);
        return modelMapper.map(findSchedule, ScheduleResponseDto.class);
    }

    @GetMapping
    public List<ScheduleResponseDto> getAllSchedule(@RequestParam("pageNum") int pageNum,
                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        List<Schedule> scheduleList = scheduleService.findAll(pageNum, pageSize);
        List<ScheduleResponseDto> dtoList = new ArrayList<>();
        for (Schedule schedule : scheduleList) {
            dtoList.add(modelMapper.map(schedule, ScheduleResponseDto.class));
        }
        return dtoList;
    }

    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId,
                                              @RequestBody ScheduleUpdateDto requestDto) {
        Schedule updated = scheduleService.update(scheduleId, requestDto);
        return modelMapper.map(updated, ScheduleResponseDto.class);
    }

    @DeleteMapping("/{scheduleId}")
    public String removeSchedule(@PathVariable Long scheduleId) {
        scheduleService.removeById(scheduleId);
        return "ok";
    }
}
