package com.jhpark.schedulerdevelop.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private final Long id;
    private final String name;
    private final String contents;
    private final LocalDateTime createdDate;
    private final LocalDateTime modDate;
}
