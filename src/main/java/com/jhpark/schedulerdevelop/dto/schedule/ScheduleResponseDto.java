package com.jhpark.schedulerdevelop.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String username;
    private final LocalDateTime createdDate;
    private final LocalDateTime modDate;
}
