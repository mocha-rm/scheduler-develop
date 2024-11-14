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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;


    public Schedule(String title, String contents) {

        this.title = title;
        this.contents = contents;
    }

    public Schedule() {

    }
}
