package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.schedule.ScheduleResponseDto;
import com.jhpark.schedulerdevelop.entity.Schedule;
import com.jhpark.schedulerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents) {
        Schedule savedSchedule = new Schedule(title, contents);
        scheduleRepository.save(savedSchedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }
}
