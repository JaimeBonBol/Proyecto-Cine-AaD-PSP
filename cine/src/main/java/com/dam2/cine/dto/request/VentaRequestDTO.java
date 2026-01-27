package com.dam2.cine.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class VentaRequestDTO {
    @NotNull
    private Long usuarioId; // Quién compra

    @NotEmpty(message = "Debes comprar al menos una entrada")
    private List<EntradaRequestDTO> entradas; // Lista de tickets
}