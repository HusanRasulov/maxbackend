package com.example.backend.Payload.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqMessage {
    private UUID sender;

    private UUID receiver;

    private String message;

    private LocalDateTime time;
}
