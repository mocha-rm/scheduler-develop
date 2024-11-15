package com.jhpark.schedulerdevelop.controller;

import com.jhpark.schedulerdevelop.dto.schedule.ScheduleRequestDto;
import com.jhpark.schedulerdevelop.dto.schedule.ScheduleResponseDto;
import com.jhpark.schedulerdevelop.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * 일정 생성
     * @apiNote localhost:8080/schedules/{id}
     * @param id (유저 id)
     * @param dto
     * @return ResponseEntity
     */
    @PostMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> save(@PathVariable Long id, @RequestBody @Valid ScheduleRequestDto dto) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(dto.getTitle(), dto.getContents(), id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 일정 조회
     * @apiNote localhost:8080/schedules
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> allSchedules = scheduleService.findAll();
        return new ResponseEntity<>(allSchedules, HttpStatus.OK);
    }

    /**
     * 선택 일정 조회
     * @apiNote localhost:8080/schedules/{id}
     * @param id (일정 id)
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.findById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    /**
     * 일정 수정
     * @apiNote localhost:8080/schedules/{id}
     * @param id (일정 id)
     * @param dto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> update(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto updatedSchedule = scheduleService.update(id, dto.getTitle(), dto.getContents());
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    /**
     * 일정 삭제
     * @apiNote localhost:8080/schedules/{id}
     * @param id (일정 id)
     * @return Void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
