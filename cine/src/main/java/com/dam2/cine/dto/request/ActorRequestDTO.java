package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActorRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
}