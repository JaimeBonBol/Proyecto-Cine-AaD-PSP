package com.dam2.cine.dto.response;

import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String rol; // Devolvemos el nombre del rol (ej: "ADMIN") directamente
}