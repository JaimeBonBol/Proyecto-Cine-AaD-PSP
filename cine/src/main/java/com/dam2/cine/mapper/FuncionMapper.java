package com.dam2.cine.mapper;

import com.dam2.cine.model.Funcion;
import com.dam2.cine.dto.request.FuncionRequestDTO;
import com.dam2.cine.dto.response.FuncionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FuncionMapper {

    // Ignoramos relaciones por ID (Pelicula y Sala). El servicio las buscará.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pelicula", ignore = true)
    @Mapping(target = "sala", ignore = true)
    @Mapping(target = "entradas", ignore = true)
    Funcion toEntity(FuncionRequestDTO dto);

    /*
     * TO DTO:
     * Aplanamos la estructura: Sacamos el Título de la Peli y el Nombre de la Sala
     * para que el Frontend no tenga que hacer 3 peticiones distintas.
     */
    @Mapping(source = "pelicula.titulo", target = "peliculaTitulo")
    @Mapping(source = "sala.nombre", target = "salaNombre")
    FuncionResponseDTO toDTO(Funcion entity);
    
    List<FuncionResponseDTO> toDTOList(List<Funcion> funciones);
}