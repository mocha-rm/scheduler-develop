package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.comment.CommentResponseDto;
import com.jhpark.schedulerdevelop.entity.Comment;
import com.jhpark.schedulerdevelop.entity.Schedule;
import com.jhpark.schedulerdevelop.entity.User;
import com.jhpark.schedulerdevelop.repository.CommentRepository;
import com.jhpark.schedulerdevelop.repository.ScheduleRepository;
import com.jhpark.schedulerdevelop.repository.UserRepository;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CommentResponseDto writeComment(Long scheduleId, String userEmail, String contents) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User findUser = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Comment comment = new Comment(contents, findUser, findSchedule);
        commentRepository.save(comment);

        return new CommentResponseDto(comment.getId(), comment.getUser().getName(), comment.getContents());
    }
}
