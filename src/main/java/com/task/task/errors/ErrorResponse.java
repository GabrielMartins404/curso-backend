package com.task.task.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data // Gera Getters, Setters, toString, etc.
@AllArgsConstructor // Gera um construtor com todos os argumentos
public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;
    private String specialCode;
    //private String actionMethod;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss") // Use HH para formato 24h
    private LocalDateTime timestamp;
}