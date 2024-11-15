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

    /**
     * 댓글 생성 (세션에 저장된 유저정보를 이용)
     * @apiNote localhost:8080/schedules/{id}/comments
     * @param id (일정 id)
     * @param dto
     * @param request
     * @return ResponseEntity
     */
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

    /**
     * 댓글 전체 조회
     * @apiNote localhost:8080/schedules/{id}/comments
     * @param id (일정 id)
     * @return ResponseEntity
     */
    @GetMapping("/{id}/comments")
    public  ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long id) {
        List<CommentResponseDto> allComments = commentService.findAll(id);
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    /**
     *  댓글 수정
     * @apiNote localhost:8080/schedules/comments/{id}
     * @param id (댓글 id)
     * @param dto
     * @return ResponseEntity
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateById(@PathVariable Long id, @RequestBody CommentRequestDto dto) {
        CommentResponseDto commentResponseDto = commentService.updateById(id, dto.getContents());
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 삭제
     * @apiNote localhost:8080/schedules/comments/{id}
     * @param id (댓글 id)
     * @return Void
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
