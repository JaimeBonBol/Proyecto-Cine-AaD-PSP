package com.dam2.cine.service;

import com.dam2.cine.model.Entrada;
import com.dam2.cine.model.EstadoEntrada;
import com.dam2.cine.model.Funcion;
import com.dam2.cine.repository.EntradaRepository;
import com.dam2.cine.repository.FuncionRepository;
import com.dam2.cine.dto.request.EntradaRequestDTO;
import com.dam2.cine.dto.response.EntradaResponseDTO;
import com.dam2.cine.mapper.EntradaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final FuncionRepository funcionRepository;
    private final EntradaMapper entradaMapper;

    public List<EntradaResponseDTO> findAll() {
        return entradaRepository.findAll().stream()
                .map(entradaMapper::toDTO)
                .toList();
    }

    public EntradaResponseDTO findById(Long id) {
        return entradaRepository.findById(id)
                .map(entradaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrada no encontrada"));
    }

    @Transactional
    public EntradaResponseDTO save(EntradaRequestDTO dto) {
        Entrada entrada = entradaMapper.toEntity(dto);

        Funcion funcion = funcionRepository.findById(dto.getFuncionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));
        entrada.setFuncion(funcion);

        entrada.setCodigo(UUID.randomUUID().toString().substring(0, 8));
        entrada.setEstado(EstadoEntrada.PENDIENTE);

        // Recordatorio: Las entradas sueltas necesitan venta, pero aquí permitimos la creación 
        // y que falle SQL si viola la FK, o se asume que se añadirá la venta después.
        // Ojo: Si la BD tiene nullable=false en venta_id, esto fallará si no asignas venta.
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se pueden crear entradas sueltas. Usa VentaService.");
    }

    @Transactional
    public EntradaResponseDTO update(Long id, EntradaRequestDTO dto) {
        Entrada entrada = entradaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrada no encontrada"));

        if (!entrada.getFuncion().getId().equals(dto.getFuncionId())) {
            Funcion nuevaFuncion = funcionRepository.findById(dto.getFuncionId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nueva función no encontrada"));
            entrada.setFuncion(nuevaFuncion);
        }

        entrada.setFila(dto.getFila());
        entrada.setAsiento(dto.getAsiento());

        return entradaMapper.toDTO(entradaRepository.save(entrada));
    }

    @Transactional
    public void delete(Long id) {
        if (!entradaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Entrada no encontrada");
        }
        entradaRepository.deleteById(id);
    }
}