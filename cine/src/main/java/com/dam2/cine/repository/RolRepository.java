package com.dam2.cine.repository;

import com.dam2.cine.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    // Necesitamos buscar roles por nombre para asignar "ROLE_USER" o "ROLE_ADMIN"
    Optional<Rol> findByNombre(String nombre);
}