package com.dam2.cine.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    
    // N Usuarios -> 1 Rol
    @ManyToOne(fetch = FetchType.EAGER) // Queremos saber el rol siempre al cargar el usuario
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    // 1 Usuario -> N Ventas (Historial de compras)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Venta> ventas = new ArrayList<>();
}