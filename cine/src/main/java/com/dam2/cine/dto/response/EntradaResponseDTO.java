package com.dam2.cine.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EntradaResponseDTO {
    private Long id;
    private int fila;
    private int asiento;
    
    // Datos útiles para imprimir en la entrada
    private String peliculaTitulo;
    private String salaNombre;
    private LocalDate fecha;
    private LocalTime hora;
    private Double precio;
}