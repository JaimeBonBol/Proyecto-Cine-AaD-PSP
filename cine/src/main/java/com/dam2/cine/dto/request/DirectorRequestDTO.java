package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DirectorRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}