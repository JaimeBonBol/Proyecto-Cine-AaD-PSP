package com.dam2.cine.controller;
import com.dam2.cine.dto.request.RolRequestDTO;
import com.dam2.cine.dto.response.RolResponseDTO;
import com.dam2.cine.service.RolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {
    private final RolService service;

    @GetMapping
    public List<RolResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public RolResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RolResponseDTO save(@RequestBody @Valid RolRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public RolResponseDTO update(@PathVariable Long id, @RequestBody @Valid RolRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}