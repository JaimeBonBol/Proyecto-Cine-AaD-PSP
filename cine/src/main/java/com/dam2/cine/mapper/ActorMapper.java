package com.dam2.cine.mapper;

import com.dam2.cine.model.Actor;
import com.dam2.cine.dto.request.ActorRequestDTO;
import com.dam2.cine.dto.response.ActorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    // Al igual que Director, ignoramos ID y la lista de películas al crear.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "peliculas", ignore = true)
    Actor toEntity(ActorRequestDTO dto);

    // Automático: id -> id, nombre -> nombre
    ActorResponseDTO toDTO(Actor entity);
}