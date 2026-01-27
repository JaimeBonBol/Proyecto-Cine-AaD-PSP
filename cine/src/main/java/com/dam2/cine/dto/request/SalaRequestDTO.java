package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class SalaRequestDTO {
    @NotBlank(message = "El nombre de la sala es obligatorio")
    private String nombre;

    @Positive(message = "La capacidad debe ser positiva")
    private int capacidad;
}