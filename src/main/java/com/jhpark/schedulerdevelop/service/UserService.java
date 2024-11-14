package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.user.LoginResponseDto;
import com.jhpark.schedulerdevelop.dto.user.UserResponseDto;
import com.jhpark.schedulerdevelop.entity.User;
import com.jhpark.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(String name, String email, String password) {
        User newUser = new User(name, email, password);
        userRepository.save(newUser);

        return new UserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return  userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getEmail());
    }

    public void delete(Long id) {
        userRepository.delete(userRepository.findByIdOrElseThrow(id));
    }

    public LoginResponseDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 이메일 입니다."));

        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다");
        }

        return new LoginResponseDto(user.getName(), user.getEmail(), "로그인 성공 !");
    }
}
