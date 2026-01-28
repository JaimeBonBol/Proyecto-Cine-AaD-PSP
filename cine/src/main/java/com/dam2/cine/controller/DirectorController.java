package com.dam2.cine.controller;

import com.dam2.cine.dto.request.DirectorRequestDTO;
import com.dam2.cine.dto.response.DirectorResponseDTO;
import com.dam2.cine.service.DirectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directores")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    public List<DirectorResponseDTO> findAll() {
        // Devuelve 200 OK por defecto
        return directorService.findAll();
    }

    @GetMapping("/{id}")
    public DirectorResponseDTO findById(@PathVariable Long id) {
        // Si falla, el servicio lanza la excepción y Spring devuelve 404
        return directorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Forzamos el 201 Created
    public DirectorResponseDTO save(@RequestBody @Valid DirectorRequestDTO dto) {
        return directorService.save(dto);
    }

    @PutMapping("/{id}")
    public DirectorResponseDTO update(@PathVariable Long id, @RequestBody @Valid DirectorRequestDTO dto) {
        return directorService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // Devuelve 204 (Sin contenido) al borrar
    public void delete(@PathVariable Long id) {
        directorService.delete(id);
    }
}