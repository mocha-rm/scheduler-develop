package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.schedule.ScheduleResponseDto;
import com.jhpark.schedulerdevelop.entity.Schedule;
import com.jhpark.schedulerdevelop.entity.User;
import com.jhpark.schedulerdevelop.repository.ScheduleRepository;
import com.jhpark.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto save(String title, String contents, Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        Schedule savedSchedule = new Schedule(title, contents, user);
        scheduleRepository.save(savedSchedule);

        return new ScheduleResponseDto(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContents(), savedSchedule.getUsername());
    }

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> all = scheduleRepository.findAll();
        return all.stream()
                .map(schedule -> new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getName()))
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getName());
    }

    public ScheduleResponseDto update(Long id, String title, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        findSchedule.updateSchedule(title, contents);
        scheduleRepository.save(findSchedule);

        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(), findSchedule.getUser().getName());
    }

    public void delete(Long id) {
        scheduleRepository.delete(scheduleRepository.findByIdOrElseThrow(id));
    }
}
