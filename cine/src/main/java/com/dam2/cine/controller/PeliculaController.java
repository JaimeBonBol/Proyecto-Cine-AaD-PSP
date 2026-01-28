package com.dam2.cine.controller;

import com.dam2.cine.dto.request.PeliculaRequestDTO;
import com.dam2.cine.dto.response.PeliculaResponseDTO;
import com.dam2.cine.service.PeliculaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@RequiredArgsConstructor
public class PeliculaController {

    private final PeliculaService peliculaService;

    @GetMapping
    public List<PeliculaResponseDTO> findAll() {
        return peliculaService.findAll();
    }

    @GetMapping("/{id}")
    public PeliculaResponseDTO findById(@PathVariable Long id) {
        return peliculaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PeliculaResponseDTO save(@RequestBody @Valid PeliculaRequestDTO dto) {
        return peliculaService.save(dto);
    }

    @PutMapping("/{id}")
    public PeliculaResponseDTO update(@PathVariable Long id, @RequestBody @Valid PeliculaRequestDTO dto) {
        return peliculaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        peliculaService.delete(id);
    }
}