package com.example.backend.Repository;

import com.example.backend.Entity.CourseVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseVideoRepo extends JpaRepository<CourseVideo, UUID> {

}
