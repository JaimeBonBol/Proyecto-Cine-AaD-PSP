package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "peliculas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private int duracion;

    private int edadMinima;

    // N Películas -> 1 Director
    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    // N Películas <-> M Actores (Tabla intermedia automática)
    @ManyToMany
    @JoinTable(
        name = "peliculas_actores",
        joinColumns = @JoinColumn(name = "pelicula_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actores = new ArrayList<>();

    // 1 Película -> N Funciones (Sesiones donde se proyecta)
    @OneToMany(mappedBy = "pelicula", fetch = FetchType.LAZY)
    private List<Funcion> funciones = new ArrayList<>();
}