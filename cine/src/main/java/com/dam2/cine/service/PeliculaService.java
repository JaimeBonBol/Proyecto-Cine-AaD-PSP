package com.dam2.cine.service;

import com.dam2.cine.model.Actor;
import com.dam2.cine.model.Director;
import com.dam2.cine.model.Pelicula;
import com.dam2.cine.repository.ActorRepository;
import com.dam2.cine.repository.DirectorRepository;
import com.dam2.cine.repository.PeliculaRepository;
import com.dam2.cine.dto.request.PeliculaRequestDTO;
import com.dam2.cine.dto.response.PeliculaResponseDTO;
import com.dam2.cine.mapper.PeliculaMapper;
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
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;
    private final PeliculaMapper peliculaMapper;

    public List<PeliculaResponseDTO> findAll() {
        return peliculaRepository.findAll().stream()
                .map(peliculaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PeliculaResponseDTO findById(Long id) {
        return peliculaRepository.findById(id)
                .map(peliculaMapper::toDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada"));
    }

    @Transactional
    public PeliculaResponseDTO save(PeliculaRequestDTO dto) {
        Pelicula pelicula = peliculaMapper.toEntity(dto);

        Director director = directorRepository.findById(dto.getDirectorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado"));
        pelicula.setDirector(director);

        if (dto.getActorIds() != null && !dto.getActorIds().isEmpty()) {
            List<Actor> actores = actorRepository.findAllById(dto.getActorIds());
            pelicula.setActores(actores);
        }

        return peliculaMapper.toDTO(peliculaRepository.save(pelicula));
    }

    @Transactional
    public PeliculaResponseDTO update(Long id, PeliculaRequestDTO dto) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada"));

        pelicula.setTitulo(dto.getTitulo());
        pelicula.setDuracion(dto.getDuracion());
        pelicula.setEdadMinima(dto.getEdadMinima());

        if (dto.getDirectorId() != null) {
            Director director = directorRepository.findById(dto.getDirectorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Director no encontrado"));
            pelicula.setDirector(director);
        }

        if (dto.getActorIds() != null) {
            List<Actor> actores = actorRepository.findAllById(dto.getActorIds());
            pelicula.setActores(actores);
        }

        return peliculaMapper.toDTO(peliculaRepository.save(pelicula));
    }

    @Transactional
    public void delete(Long id) {
        if (!peliculaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada");
        }
        peliculaRepository.deleteById(id);
    }
}