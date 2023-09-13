package com.example.backend.Repository;

import com.example.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    List<User> findAllByIdIsNot(UUID myId);

//    @Query(value = """
//        SELECT * FROM users where BIN_TO_UUID(id)!=:myId
//""",nativeQuery = true)
//    List<User> findAllUsersWithoutMe(UUID myId);
}
