package com.example.backend.Services.CourseService;

import com.example.backend.Entity.Course;
import com.example.backend.Entity.CourseUser;
import com.example.backend.Entity.User;
import com.example.backend.Projection.CourseProjection;
import com.example.backend.Repository.CourseRepo;
import com.example.backend.Repository.CourseUserRepo;
import com.example.backend.Repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;
    private final CourseUserRepo courseUserRepo;
    private final UserRepo userRepo;

    @Override
    public HttpEntity<?> getCourses() {
        return ResponseEntity.ok(courseRepo.findAll());
    }

    @Override
    public HttpEntity<?> saveCoursesToUserCache(UUID userId, UUID courseId) {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with ID:" + courseId));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
        CourseUser save = courseUserRepo.save(
                new CourseUser(
                        course,
                        user
                )
        );
        return ResponseEntity.ok(save);
    }

    @Override
    public HttpEntity<?> getCoursesByUserId(UUID userId) {
        List<CourseProjection> courses = courseUserRepo.getCoursesAndVideosByUserId(userId);
        return ResponseEntity.ok(courses);
    }
}
