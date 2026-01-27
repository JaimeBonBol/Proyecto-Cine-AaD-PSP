package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.util.List;

@Data
public class PeliculaRequestDTO {
    @NotBlank(message = "El título es obligatorio")
    private String titulo;
    
    private String genero;
    
    @Positive
    private int duracion;

    @NotNull(message = "Debes especificar el ID del director")
    private Long directorId; // Referencia por ID [cite: 375]

    private List<Long> actorIds; // Referencias por ID [cite: 372]
}