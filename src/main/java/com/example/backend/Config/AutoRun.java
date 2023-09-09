package com.example.backend.Config;

import com.example.backend.Entity.*;
import com.example.backend.Enums.UserRoles;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<User> users = userRepo.findAll();
        List<Role> roles = roleRepo.findAll();
        if (roles.isEmpty()){
            saveRoles();
        }
        if (users.isEmpty()){
            saveUser();
        }
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
