package com.dam2.cine.mapper;

import com.dam2.cine.model.Director;
import com.dam2.cine.dto.request.DirectorRequestDTO;
import com.dam2.cine.dto.response.DirectorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// @Mapper: Le dice a Spring que esto es un componente inyectable.
@Mapper(componentModel = "spring")
public interface DirectorMapper {

    /* * CONVERTIR DE DTO A ENTIDAD (Para guardar en BD)
     * ------------------------------------------------
     * 1. target="id": Se IGNORA. La base de datos decide el ID (Autoincremental).
     * 2. target="peliculas": Se IGNORA. Al crear un director nuevo, 
     * la lista de películas empieza vacía.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "peliculas", ignore = true)
    Director toEntity(DirectorRequestDTO dto);

    /* * CONVERTIR DE ENTIDAD A DTO (Para enviar al Frontend)
     * ----------------------------------------------------
     * Como los campos se llaman igual (nombre -> nombre, id -> id),
     * MapStruct hace el mapeo automáticamente sin escribir nada.
     */
    DirectorResponseDTO toDTO(Director entity);
}