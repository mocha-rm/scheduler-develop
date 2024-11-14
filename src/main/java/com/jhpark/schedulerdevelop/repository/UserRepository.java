package com.jhpark.schedulerdevelop.repository;

import com.jhpark.schedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
