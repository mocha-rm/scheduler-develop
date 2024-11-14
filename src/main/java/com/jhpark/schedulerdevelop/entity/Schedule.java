package com.jhpark.schedulerdevelop.entity;

import com.jhpark.schedulerdevelop.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 200)
    private String contents;

    @ManyToOne // 유저 한명이 여러개의 일정을 가질 수 있다.
    @JoinColumn(name = "user_id")
    private User user;


    public Schedule(String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

    public Schedule() {

    }

    public void updateSchedule(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
