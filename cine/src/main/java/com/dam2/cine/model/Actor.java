package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // N Actores -> M Películas (La relación inversa)
    @ManyToMany(mappedBy = "actores", fetch = FetchType.LAZY)
    private List<Pelicula> peliculas = new ArrayList<>();
}