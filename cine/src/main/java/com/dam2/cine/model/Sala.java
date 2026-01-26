package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private int capacidad;

    // 1 Sala -> N Funciones (Aquí es donde proyectan las pelis)
    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY)
    private List<Funcion> funciones = new ArrayList<>();
}