package com.dam2.cine.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class VentaResponseDTO {
    private Long id;
    private LocalDateTime fechaCompra;
    private Double total;
    private String estado;
    private String emailUsuario; // Para saber de quién es
    
    // Lista de entradas generadas en esta venta
    private List<EntradaResponseDTO> entradas; 
}