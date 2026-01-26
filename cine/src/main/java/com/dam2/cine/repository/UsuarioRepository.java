package com.dam2.cine.repository;

import com.dam2.cine.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por su email (muy util para el login)
    Optional<Usuario> findByEmail(String email);
    
    // Para comprobar si existe antes de crear uno nuevo
    boolean existsByEmail(String email);
}