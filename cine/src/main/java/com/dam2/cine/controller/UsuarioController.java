package com.dam2.cine.controller;
import com.dam2.cine.dto.request.UsuarioRequestDTO;
import com.dam2.cine.dto.response.UsuarioResponseDTO;
import com.dam2.cine.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping
    public List<UsuarioResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public UsuarioResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO save(@RequestBody @Valid UsuarioRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public UsuarioResponseDTO update(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}