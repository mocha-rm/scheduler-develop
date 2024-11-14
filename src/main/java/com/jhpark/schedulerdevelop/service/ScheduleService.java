package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.schedule.ScheduleResponseDto;
import com.jhpark.schedulerdevelop.entity.Schedule;
import com.jhpark.schedulerdevelop.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto save(String title, String contents) {
        Schedule savedSchedule = new Schedule(title, contents);
        scheduleRepository.save(savedSchedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> all = scheduleRepository.findAll();
        return all.stream()
                .map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents()))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    public ScheduleResponseDto update(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateSchedule(title, contents);
        scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents());
    }

    public void delete(Long id) {
        scheduleRepository.delete(scheduleRepository.findByIdOrElseThrow(id));
    }
}
