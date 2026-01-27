package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class EntradaRequestDTO {
    @NotNull
    private Long funcionId; // Para qué sesión es
    
    @Positive
    private int fila;
    
    @Positive
    private int asiento;
}