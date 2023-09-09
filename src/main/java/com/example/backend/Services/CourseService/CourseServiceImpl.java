package com.example.backend.Services.CourseService;

import com.example.backend.Repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepo courseRepo;

    @Override
    public HttpEntity<?> getCourses() {
        return ResponseEntity.ok(courseRepo.findAll());
    }
}
