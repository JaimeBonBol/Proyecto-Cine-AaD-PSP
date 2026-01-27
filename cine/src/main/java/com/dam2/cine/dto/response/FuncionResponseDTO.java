package com.dam2.cine.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FuncionResponseDTO {
    private Long id;
    private LocalDateTime fechaHora;
    private Double precio;
    
    // Estos siguen siendo necesarios porque la entidad tiene objetos y aquí queremos texto
    private String peliculaTitulo; 
    private String salaNombre;
}