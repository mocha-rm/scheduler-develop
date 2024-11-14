package com.jhpark.schedulerdevelop.dto.comment;

import com.jhpark.schedulerdevelop.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CommentRequestDto {
    private final Long userId;
    private final String name;
    private final String contents;
}
