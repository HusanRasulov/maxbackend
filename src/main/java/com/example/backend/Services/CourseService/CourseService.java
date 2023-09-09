package com.example.backend.Services.CourseService;

import org.springframework.http.HttpEntity;

public interface CourseService {
    HttpEntity<?> getCourses();
}
