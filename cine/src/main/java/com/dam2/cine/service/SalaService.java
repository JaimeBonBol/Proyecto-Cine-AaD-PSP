package com.dam2.cine.service;

import com.dam2.cine.model.Sala;
import com.dam2.cine.repository.SalaRepository;
import com.dam2.cine.dto.request.SalaRequestDTO;
import com.dam2.cine.dto.response.SalaResponseDTO;
import com.dam2.cine.mapper.SalaMapper;
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
public class SalaService {

    private final SalaRepository salaRepository;
    private final SalaMapper salaMapper;

    public List<SalaResponseDTO> findAll() {
        return salaRepository.findAll().stream()
                .map(salaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SalaResponseDTO findById(Long id) {
        return salaRepository.findById(id)
                .map(salaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada"));
    }

    @Transactional
    public SalaResponseDTO save(SalaRequestDTO dto) {
        Sala sala = salaMapper.toEntity(dto);
        return salaMapper.toDTO(salaRepository.save(sala));
    }

    @Transactional
    public SalaResponseDTO update(Long id, SalaRequestDTO dto) {
        Sala sala = salaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada"));
        
        sala.setNombre(dto.getNombre());
        sala.setCapacidad(dto.getCapacidad());
        
        return salaMapper.toDTO(salaRepository.save(sala));
    }

    @Transactional
    public void delete(Long id) {
        if (!salaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala no encontrada");
        }
        salaRepository.deleteById(id);
    }
}