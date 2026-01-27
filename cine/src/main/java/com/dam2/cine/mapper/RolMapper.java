package com.dam2.cine.mapper;

import com.dam2.cine.model.Rol;
import com.dam2.cine.dto.request.RolRequestDTO;
import com.dam2.cine.dto.response.RolResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuarios", ignore = true)
    Rol toEntity(RolRequestDTO dto);

    RolResponseDTO toDTO(Rol entity);
}