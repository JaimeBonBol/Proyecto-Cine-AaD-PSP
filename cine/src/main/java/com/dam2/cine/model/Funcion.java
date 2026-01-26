package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Double precio;

    // N Funciones -> 1 Película
    @ManyToOne
    @JoinColumn(name = "pelicula_id", nullable = false)
    private Pelicula pelicula;

    // N Funciones -> 1 Sala
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    // 1 Función -> N Entradas (Tickets vendidos para esta sesión)
    @OneToMany(mappedBy = "funcion", fetch = FetchType.LAZY)
    private List<Entrada> entradas = new ArrayList<>();
}