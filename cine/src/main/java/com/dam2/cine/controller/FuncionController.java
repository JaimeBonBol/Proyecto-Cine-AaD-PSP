package com.dam2.cine.controller;
import com.dam2.cine.dto.request.FuncionRequestDTO;
import com.dam2.cine.dto.response.FuncionResponseDTO;
import com.dam2.cine.service.FuncionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@RequiredArgsConstructor
public class FuncionController {
    private final FuncionService service;

    @GetMapping
    public List<FuncionResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public FuncionResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FuncionResponseDTO save(@RequestBody @Valid FuncionRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public FuncionResponseDTO update(@PathVariable Long id, @RequestBody @Valid FuncionRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}