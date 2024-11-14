package com.jhpark.schedulerdevelop.dto.comment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentResponseDto {
    private final Long id;
    private final String name;
    private final String contents;
}
