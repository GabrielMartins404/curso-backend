package com.task.task.errors;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String descricao;
    private List<String> detalhamento;
    private LocalDateTime dataHora;
}
