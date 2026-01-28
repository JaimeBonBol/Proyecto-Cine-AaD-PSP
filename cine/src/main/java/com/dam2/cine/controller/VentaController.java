package com.dam2.cine.controller;

import com.dam2.cine.dto.request.VentaRequestDTO;
import com.dam2.cine.dto.response.VentaResponseDTO;
import com.dam2.cine.service.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @GetMapping
    public List<VentaResponseDTO> findAll() {
        return ventaService.findAll();
    }

    @GetMapping("/{id}")
    public VentaResponseDTO findById(@PathVariable Long id) {
        return ventaService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentaResponseDTO crearVenta(@RequestBody @Valid VentaRequestDTO dto) {
        // @Valid revisa que usuarioId no sea nulo y que la lista de entradas no esté vacía
        return ventaService.save(dto);
    }
    
    // UPDATE y DELETE opcionales según tus necesidades de negocio
    @PutMapping("/{id}")
    public VentaResponseDTO update(@PathVariable Long id, @RequestBody @Valid VentaRequestDTO dto) {
        return ventaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ventaService.delete(id);
    }
}