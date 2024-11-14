package com.jhpark.schedulerdevelop.dto.schedule;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ScheduleRequestDto {
    private final String title;
    private  final String contents;
}
