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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponseDto> writeComment(@PathVariable Long id, @RequestBody CommentRequestDto dto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("sessionKey") == null) {
            throw new RuntimeException("세션을 찾을 수 없습니다.");
        }

        String userEmail = String.valueOf(session.getAttribute("sessionKey"));

        CommentResponseDto commentResponseDto = commentService.writeComment(id, userEmail, dto.getContents());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/comments")
    public  ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> allComments = commentService.findAll(id);
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateById(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        CommentResponseDto commentResponseDto = commentService.updateById(id, dto.getContents());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
