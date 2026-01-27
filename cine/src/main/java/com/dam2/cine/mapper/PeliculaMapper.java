package com.dam2.cine.mapper;

import com.dam2.cine.model.Pelicula;
import com.dam2.cine.dto.request.PeliculaRequestDTO;
import com.dam2.cine.dto.response.PeliculaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// 'uses': Le decimos que puede usar los mappers de Director y Actor 
// para convertir los objetos anidados en el ResponseDTO.
@Mapper(componentModel = "spring", uses = {DirectorMapper.class, ActorMapper.class})
public interface PeliculaMapper {

    /*
     * TO ENTITY:
     * El DTO trae 'directorId' y 'actorIds'.
     * Ignoramos 'director' y 'actores' aquí.
     * El Servicio usará esos IDs para buscar en los Repositorios 
     * y llenar la entidad correctamente.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "director", ignore = true) 
    @Mapping(target = "actores", ignore = true)  
    @Mapping(target = "funciones", ignore = true)
    Pelicula toEntity(PeliculaRequestDTO dto);

    /*
     * TO DTO:
     * Gracias al 'uses' de arriba, MapStruct ve que Pelicula tiene un Director,
     * llama a DirectorMapper y lo convierte a DirectorResponseDTO automáticamente.
     */
    PeliculaResponseDTO toDTO(Pelicula entity);
    
    // Método útil para cuando pidamos la lista de todas las películas
    List<PeliculaResponseDTO> toDTOList(List<Pelicula> peliculas);
}