package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FuncionRequestDTO {
    @NotNull
    private LocalDateTime fechaHora;

    @Positive
    private Double precio;
    
    @NotNull
    private Long peliculaId; // ID de la peli
    
    @NotNull
    private Long salaId;     // ID de la sala
}