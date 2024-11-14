package com.jhpark.schedulerdevelop.controller;

import com.jhpark.schedulerdevelop.dto.comment.CommentRequestDto;
import com.jhpark.schedulerdevelop.dto.comment.CommentResponseDto;
import com.jhpark.schedulerdevelop.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/{id}")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> writeComment(@PathVariable Long id, @RequestBody CommentRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sessionKey") == null) {
            throw new RuntimeException("세션을 찾을 수 없습니다.");
        }

        String userEmail = String.valueOf(session.getAttribute("sessionKey"));

        CommentResponseDto responseDto = commentService.writeComment(id, userEmail, dto.getContents());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
