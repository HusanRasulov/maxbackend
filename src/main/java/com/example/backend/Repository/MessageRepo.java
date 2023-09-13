package com.example.backend.Repository;

import com.example.backend.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MessageRepo extends JpaRepository<Message, UUID> {
    @Query(value = """
SELECT *
FROM message
where (BIN_TO_UUID(sender_id) = :senderId
    and BIN_TO_UUID(receiver_id) = :receiverId)
   OR (BIN_TO_UUID(sender_id) = :receiverId and BIN_TO_UUID(receiver_id) = :senderId)
ORDER BY time
""",nativeQuery = true)
    List<Message> findUserMessages(UUID senderId, UUID receiverId);
}