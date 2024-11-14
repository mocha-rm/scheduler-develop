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
