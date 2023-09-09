package com.example.backend.Config;

import com.example.backend.Entity.*;
import com.example.backend.Enums.UserRoles;
import com.example.backend.Repository.CourseRepo;
import com.example.backend.Repository.CourseVideoRepo;
import com.example.backend.Repository.RoleRepo;
import com.example.backend.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AutoRun implements CommandLineRunner {
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;
    private final CourseVideoRepo courseVideoRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepo.findAll();
        List<Role> roles = roleRepo.findAll();
        List<Course> courses = courseRepo.findAll();
        List<CourseVideo> courseVideos = courseVideoRepo.findAll();
        if (roles.isEmpty()) {
            saveRoles();
        }
        if (users.isEmpty()) {
            saveUser();
        }
        if (courses.isEmpty() && courseVideos.isEmpty()) {
            saveCourse();
        }
    }


    private void saveCourse() {
        List<Course> courses = List.of(
                new Course("Frontend", "you can learn frontend"),
                new Course("Angular", "Angular is one of the best"),
                new Course("Node.js", "Node.js is one of the best"),
                new Course("Javascript", "Javascript is one of the best"),
                new Course("Firebase", "Firebase is one of the best")
        );
        List<Course> courseList = courseRepo.saveAll(courses);
        List<CourseVideo> videos = List.of(
                new CourseVideo(
                        courseList.get(0),
                        "Html",
                        "https://www.youtube.com/embed/qz0aGYrrlhU",
                        false
                ),
                new CourseVideo(
                        courseList.get(0),
                        "css",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(1),
                        "state",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(1),
                        "setState",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(2),
                        "express",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(2),
                        "run time environment",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(3),
                        "real time database",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(3),
                        "firestore",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(4),
                        "mockito",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                ),
                new CourseVideo(
                        courseList.get(4),
                        "unit test",
                        "https://www.youtube.com/embed/1PnVor36_40",
                        false
                )
        );
        courseVideoRepo.saveAll(videos);
    }


    private void saveUser() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(roleRepo.findByName(UserRoles.ROLE_STUDENT));
        User user = User.builder()
                .phone("990453027")
                .password(passwordEncoder.encode("00000000"))
                .roles(roleList)
                .build();
        userRepo.save(user);
    }

    private void saveRoles() {
        roleRepo.saveAll(new ArrayList<>(List.of(
                new Role(1, UserRoles.ROLE_STUDENT),
                new Role(2, UserRoles.ROLE_TEACHER)
        )));
    }
}
