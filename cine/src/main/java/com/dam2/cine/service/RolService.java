package com.dam2.cine.service;

import com.dam2.cine.model.Rol;
import com.dam2.cine.repository.RolRepository;
import com.dam2.cine.dto.request.RolRequestDTO;
import com.dam2.cine.dto.response.RolResponseDTO;
import com.dam2.cine.mapper.RolMapper;
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
public class RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public List<RolResponseDTO> findAll() {
        return rolRepository.findAll().stream()
                .map(rolMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RolResponseDTO findById(Long id) {
        return rolRepository.findById(id)
                .map(rolMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));
    }

    @Transactional
    public RolResponseDTO save(RolRequestDTO dto) {
        Rol rol = rolMapper.toEntity(dto);
        return rolMapper.toDTO(rolRepository.save(rol));
    }

    @Transactional
    public RolResponseDTO update(Long id, RolRequestDTO dto) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado"));
        rol.setNombre(dto.getNombre());
        return rolMapper.toDTO(rolRepository.save(rol));
    }
    
    @Transactional
    public void delete(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol no encontrado");
        }
        rolRepository.deleteById(id);
    }
}