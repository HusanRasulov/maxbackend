package com.example.backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    private Course course;

    private String name;

    private String youtubeId;

    private Boolean isFinished;

    public CourseVideo(Course course, String name, String youtubeId, Boolean isFinished) {
        this.course = course;
        this.name = name;
        this.youtubeId = youtubeId;
        this.isFinished = isFinished;
    }
}