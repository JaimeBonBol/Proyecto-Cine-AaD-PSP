package com.dam2.cine.controller;
import com.dam2.cine.dto.request.SalaRequestDTO;
import com.dam2.cine.dto.response.SalaResponseDTO;
import com.dam2.cine.service.SalaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/salas")
@RequiredArgsConstructor
public class SalaController {
    private final SalaService service;

    @GetMapping
    public List<SalaResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public SalaResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalaResponseDTO save(@RequestBody @Valid SalaRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public SalaResponseDTO update(@PathVariable Long id, @RequestBody @Valid SalaRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}