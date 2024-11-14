package com.jhpark.schedulerdevelop.entity;

import com.jhpark.schedulerdevelop.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 40)
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User() {

    }
}
