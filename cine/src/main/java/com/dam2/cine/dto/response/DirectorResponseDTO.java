package com.dam2.cine.dto.response;

import lombok.Data;

// No tiene lista de peliculas, cortando así bucle infinito.
@Data
public class DirectorResponseDTO {
    private Long id;
    private String nombre;
}