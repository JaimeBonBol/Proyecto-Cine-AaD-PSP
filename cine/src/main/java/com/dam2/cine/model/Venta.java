package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    private Double total;

    private String metodoPago;

    private String estado; // "PAGADA", "CANCELADA"

    // N Ventas -> 1 Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // 1 Venta -> N Entradas
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Entrada> entradas = new ArrayList<>();
}