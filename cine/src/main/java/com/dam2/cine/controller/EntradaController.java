package com.dam2.cine.controller;
import com.dam2.cine.dto.request.EntradaRequestDTO;
import com.dam2.cine.dto.response.EntradaResponseDTO;
import com.dam2.cine.service.EntradaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entradas")
@RequiredArgsConstructor
public class EntradaController {
    private final EntradaService service;

    @GetMapping
    public List<EntradaResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public EntradaResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    // Ojo: Crear entradas sueltas fallará si no tiene lógica en el servicio, 
    // pero el controlador se deja expuesto por si acaso.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntradaResponseDTO save(@RequestBody @Valid EntradaRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public EntradaResponseDTO update(@PathVariable Long id, @RequestBody @Valid EntradaRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}