package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolRequestDTO {
    @NotBlank
    private String nombre;
}