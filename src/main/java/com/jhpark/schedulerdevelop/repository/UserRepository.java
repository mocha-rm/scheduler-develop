package com.jhpark.schedulerdevelop.repository;

import com.jhpark.schedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저를 찾을 수 없습니다."));
    }

    /**
     * @apiNote Spring Data JPA 가 인터페이스의 메서드 이름을 분석해 자동으로 구현체를 생성해 준다.
     * @param email
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);
}
