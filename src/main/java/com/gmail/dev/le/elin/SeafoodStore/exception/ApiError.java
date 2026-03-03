package com.gmail.dev.le.elin.SeafoodStore.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;
}
