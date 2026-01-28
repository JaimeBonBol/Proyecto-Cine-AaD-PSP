package com.dam2.cine.controller;
import com.dam2.cine.dto.request.ActorRequestDTO;
import com.dam2.cine.dto.response.ActorResponseDTO;
import com.dam2.cine.service.ActorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actores")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService service;

    @GetMapping
    public List<ActorResponseDTO> findAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public ActorResponseDTO findById(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ActorResponseDTO save(@RequestBody @Valid ActorRequestDTO dto) { return service.save(dto); }

    @PutMapping("/{id}")
    public ActorResponseDTO update(@PathVariable Long id, @RequestBody @Valid ActorRequestDTO dto) { return service.update(id, dto); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}