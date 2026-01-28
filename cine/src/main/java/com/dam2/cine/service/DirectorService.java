package com.dam2.cine.service;

import com.dam2.cine.model.Director;
import com.dam2.cine.repository.DirectorRepository;
import com.dam2.cine.dto.request.DirectorRequestDTO;
import com.dam2.cine.dto.response.DirectorResponseDTO;
import com.dam2.cine.mapper.DirectorMapper;
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
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final DirectorMapper directorMapper;

    public List<DirectorResponseDTO> findAll() {
        return directorRepository.findAll().stream()
                .map(directorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DirectorResponseDTO findById(Long id) {
        return directorRepository.findById(id)
                .map(directorMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado con ID: " + id));
    }

    @Transactional
    public DirectorResponseDTO save(DirectorRequestDTO dto) {
        Director director = directorMapper.toEntity(dto);
        return directorMapper.toDTO(directorRepository.save(director));
    }

    @Transactional
    public DirectorResponseDTO update(Long id, DirectorRequestDTO dto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado con ID: " + id));
        
        director.setNombre(dto.getNombre());
        return directorMapper.toDTO(directorRepository.save(director));
    }

    @Transactional
    public void delete(Long id) {
        if (!directorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede borrar. Director no encontrado con ID: " + id);
        }
        directorRepository.deleteById(id);
    }
}