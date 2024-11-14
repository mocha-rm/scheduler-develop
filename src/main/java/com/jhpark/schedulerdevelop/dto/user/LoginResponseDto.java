package com.jhpark.schedulerdevelop.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class LoginResponseDto {
    private final String name;
    private final String email;
    private final String responseMessage;
}
