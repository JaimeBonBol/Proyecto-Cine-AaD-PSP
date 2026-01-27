package com.dam2.cine.mapper;

import com.dam2.cine.model.Sala;
import com.dam2.cine.dto.request.SalaRequestDTO;
import com.dam2.cine.dto.response.SalaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SalaMapper {

    // Ignoramos ID y la lista de funciones (la sala nace vacía).
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "funciones", ignore = true)
    Sala toEntity(SalaRequestDTO dto);

    // Automático: nombre -> nombre, capacidad -> capacidad
    SalaResponseDTO toDTO(Sala entity);
}