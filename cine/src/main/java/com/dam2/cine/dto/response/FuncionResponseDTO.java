package com.dam2.cine.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FuncionResponseDTO {
    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double precio;
    
    // Resumen de la peli
    private String peliculaTitulo; 
    private String salaNombre;
}