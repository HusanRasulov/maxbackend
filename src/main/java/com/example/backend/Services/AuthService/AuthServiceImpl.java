package com.example.backend.Services.AuthService;

import com.example.backend.DTO.UserDTO;
import com.example.backend.Entity.Role;
import com.example.backend.Entity.User;
import com.example.backend.Enums.UserRoles;
import com.example.backend.Payload.req.ReqLogin;
import com.example.backend.Repository.RoleRepo;
import com.example.backend.Repository.UserRepo;
import com.example.backend.Security.JwtService;
import com.example.backend.exceptions.InvalidCredentialsException;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepo usersRepository;
    private final RoleRepo roleRepo;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @SneakyThrows
    @Override
    public HttpEntity<?> register(UserDTO userDTO) {
        List<Role> roles = new ArrayList<>();
        List<Role> roleUser = roleRepo.findAllByName(UserRoles.ROLE_STUDENT);
        if (roleUser == null) {
            roles.add(roleRepo.save(new Role(1, UserRoles.ROLE_STUDENT)));
        } else {
            roles.add(roleUser.get(0));
        }
        User user = new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                roles
        );
        usersRepository.save(user);
        return ResponseEntity.ok(null);
    }


    @Override
    public HttpEntity<?> login(UserDTO userDTO) {
        Optional<User> users = usersRepository.findByPhone(userDTO.getPhone());
        if (users.isEmpty()) throw new InvalidCredentialsException();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getPhone(), userDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException();
        }
        System.out.println(userDTO.getPhone());
        User userByPhone = users.orElseThrow();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", jwtService.generateJwtToken(userByPhone));
        map.put("refresh_token", jwtService.generateJwtRefreshToken(userByPhone));
        System.out.println(userByPhone.getRoles());
        map.put("roles", userByPhone.getRoles());
        return ResponseEntity.ok(map);
    }


    @Override
    public HttpEntity<?> refreshToken(String refreshToken) {
        String id = jwtService.extractSubjectFromJwt(refreshToken);
        User users = usersRepository.findById(UUID.fromString(id)).orElseThrow();
        String access_token = jwtService.generateJwtToken(users);
        return ResponseEntity.ok(access_token);
    }

    @Override
    public User decode(String token) {
        boolean isExpired = jwtService.validateToken(token);
        User user = null;
        if (isExpired) {
            String userId = jwtService.extractSubjectFromJwt(token);
            user = usersRepository.findById(UUID.fromString(userId)).orElseThrow(() -> new RuntimeException("Cannot find User With Id:" + userId));
        }
        return user;
    }
}
