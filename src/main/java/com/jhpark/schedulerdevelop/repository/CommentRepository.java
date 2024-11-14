package com.jhpark.schedulerdevelop.repository;

import com.jhpark.schedulerdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
