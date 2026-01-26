package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "directores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    // 1 Director -> N Películas
    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY)
    private List<Pelicula> peliculas = new ArrayList<>();
}