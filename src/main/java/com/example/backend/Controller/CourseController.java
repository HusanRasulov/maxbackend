package com.example.backend.Controller;


import com.example.backend.Services.CourseService.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping
    public HttpEntity<?> getCourses() {
        return courseService.getCourses();
    }
}
