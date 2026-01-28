package com.dam2.cine.service;

import com.dam2.cine.model.Actor;
import com.dam2.cine.repository.ActorRepository;
import com.dam2.cine.dto.request.ActorRequestDTO;
import com.dam2.cine.dto.response.ActorResponseDTO;
import com.dam2.cine.mapper.ActorMapper;
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
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    public List<ActorResponseDTO> findAll() {
        return actorRepository.findAll().stream()
                .map(actorMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ActorResponseDTO findById(Long id) {
        return actorRepository.findById(id)
                .map(actorMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));
    }

    @Transactional
    public ActorResponseDTO save(ActorRequestDTO dto) {
        Actor actor = actorMapper.toEntity(dto);
        return actorMapper.toDTO(actorRepository.save(actor));
    }

    @Transactional
    public ActorResponseDTO update(Long id, ActorRequestDTO dto) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado"));
        actor.setNombre(dto.getNombre());
        return actorMapper.toDTO(actorRepository.save(actor));
    }

    @Transactional
    public void delete(Long id) {
        if (!actorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor no encontrado");
        }
        actorRepository.deleteById(id);
    }
}