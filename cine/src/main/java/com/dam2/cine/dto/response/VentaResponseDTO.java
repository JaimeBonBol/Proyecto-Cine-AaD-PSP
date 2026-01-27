package com.dam2.cine.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private Double total;
    private String estado;
    private String emailUsuario;
    
    private List<EntradaResponseDTO> entradas; 
}