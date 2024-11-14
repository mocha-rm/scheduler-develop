package com.jhpark.schedulerdevelop.controller;

import com.jhpark.schedulerdevelop.dto.schedule.ScheduleRequestDto;
import com.jhpark.schedulerdevelop.dto.schedule.ScheduleResponseDto;
import com.jhpark.schedulerdevelop.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(dto.getTitle(), dto.getContents());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }
}
