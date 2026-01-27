package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entradas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private int fila;

    private int asiento;

    private EstadoEntrada estado = EstadoEntrada.PENDIENTE;
    
    // N Entradas -> 1 Venta
    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    // N Entradas -> 1 Función (Para saber fecha, hora y sala)
    @ManyToOne
    @JoinColumn(name = "funcion_id", nullable = false)
    private Funcion funcion;
}