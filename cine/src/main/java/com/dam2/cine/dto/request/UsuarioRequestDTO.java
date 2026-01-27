package com.dam2.cine.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank @Email
    private String email;
    
    @NotBlank
    private String password; // Solo viaja en el request
    
    // Opcional: Podríamos dejar que el sistema asigne "USER" por defecto
    // o permitir enviar el ID del rol si es un panel de admin.
    private Long rolId; 
}