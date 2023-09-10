package com.example.backend.Projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface CourseProjection {
    UUID getId();

    String getCourseName();

    String getCourseDescription();

    @Value("#{@courseVideoRepo.findAllByCourseId(target.id)}")
    List<VideoProjection> getCourseVideo();
}
