package com.jhpark.schedulerdevelop.controller;

import com.jhpark.schedulerdevelop.dto.user.LoginRequestDto;
import com.jhpark.schedulerdevelop.dto.user.LoginResponseDto;
import com.jhpark.schedulerdevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
        LoginResponseDto loginResponseDto = userService.authenticate(dto.getEmail(), dto.getPassword());
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionKey", loginResponseDto.getEmail());

        return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("로그아웃 성공");
    }
}
