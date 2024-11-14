package com.jhpark.schedulerdevelop.service;

import com.jhpark.schedulerdevelop.dto.user.UserResponseDto;
import com.jhpark.schedulerdevelop.entity.User;
import com.jhpark.schedulerdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto save(String name, String email) {
        User newUser = new User(name, email);
        userRepository.save(newUser);

        return new UserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }

    public List<UserResponseDto> findAll() {
        return  userRepository.findAll()
                .stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }
}
