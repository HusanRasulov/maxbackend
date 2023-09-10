package com.example.backend.Repository;

import com.example.backend.Entity.CourseVideo;
import com.example.backend.Projection.VideoProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseVideoRepo extends JpaRepository<CourseVideo, UUID> {
    List<VideoProjection> findAllByCourseId(UUID courseId);
}
