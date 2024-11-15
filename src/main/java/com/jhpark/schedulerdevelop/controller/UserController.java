package com.jhpark.schedulerdevelop.controller;

import com.jhpark.schedulerdevelop.dto.user.UserRequestDto;
import com.jhpark.schedulerdevelop.dto.user.UserResponseDto;
import com.jhpark.schedulerdevelop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입
     * @apiNote localhost:8080/users/signup
     * @param dto
     * @return ResponseEntity
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> save(@RequestBody @Valid UserRequestDto dto) {
        UserResponseDto userResponseDto = userService.save(dto.getName(), dto.getEmail(), dto.getPassword());
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    /**
     * 전체 유저 조회
     * @apiNote localhost:8080/users
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> allUsers = userService.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    /**
     * 선택 유저 조회
     * @apiNote localhost:8080/users/{id}
     * @param id
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        UserResponseDto findUser = userService.findById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    /**
     * 유저 삭제
     * @apiNote localhost:8080/users/{id}
     * @param id
     * @return Void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
