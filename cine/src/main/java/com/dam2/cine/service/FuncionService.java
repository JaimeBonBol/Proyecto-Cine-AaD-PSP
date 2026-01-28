package com.dam2.cine.service;

import com.dam2.cine.model.Funcion;
import com.dam2.cine.model.Pelicula;
import com.dam2.cine.model.Sala;
import com.dam2.cine.repository.FuncionRepository;
import com.dam2.cine.repository.PeliculaRepository;
import com.dam2.cine.repository.SalaRepository;
import com.dam2.cine.dto.request.FuncionRequestDTO;
import com.dam2.cine.dto.response.FuncionResponseDTO;
import com.dam2.cine.mapper.FuncionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FuncionService {

    private final FuncionRepository funcionRepository;
    private final PeliculaRepository peliculaRepository;
    private final SalaRepository salaRepository;
    private final FuncionMapper funcionMapper;

    public List<FuncionResponseDTO> findAll() {
        return funcionRepository.findAll().stream()
                .map(funcionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FuncionResponseDTO findById(Long id) {
        return funcionRepository.findById(id)
                .map(funcionMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));
    }

    @Transactional
    public FuncionResponseDTO save(FuncionRequestDTO dto) {
        Funcion funcion = funcionMapper.toEntity(dto);
        configurarRelaciones(funcion, dto);
        return funcionMapper.toDTO(funcionRepository.save(funcion));
    }

    @Transactional
    public FuncionResponseDTO update(Long id, FuncionRequestDTO dto) {
        Funcion funcion = funcionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));
        
        funcion.setFechaHora(dto.getFechaHora());
        funcion.setPrecio(dto.getPrecio());
        configurarRelaciones(funcion, dto);

        return funcionMapper.toDTO(funcionRepository.save(funcion));
    }

    @Transactional
    public void delete(Long id) {
        if (!funcionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada");
        }
        funcionRepository.deleteById(id);
    }
    
    private void configurarRelaciones(Funcion funcion, FuncionRequestDTO dto) {
        if (dto.getPeliculaId() != null) {
            Pelicula pelicula = peliculaRepository.findById(dto.getPeliculaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada"));
            funcion.setPelicula(pelicula);
        }
        if (dto.getSalaId() != null) {
            Sala sala = salaRepository.findById(dto.getSalaId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada"));
            funcion.setSala(sala);
        }
    }
}