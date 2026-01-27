package com.dam2.cine.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class PeliculaResponseDTO {
    private Long id;
    private String titulo;
    private String genero;
    private int duracion;
    
    // Aquí SÍ anidamos objetos (Response), porque estos ya son planos
    private DirectorResponseDTO director; 
    private List<ActorResponseDTO> actores; 
}