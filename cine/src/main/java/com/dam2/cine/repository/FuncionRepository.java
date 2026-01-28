package com.dam2.cine.repository;

import com.dam2.cine.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    // Buscar todas las funciones de una película específica
    List<Funcion> findByPeliculaId(Long peliculaId);

}