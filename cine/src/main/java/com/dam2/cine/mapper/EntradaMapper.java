package com.dam2.cine.mapper;

import com.dam2.cine.model.Entrada;
import com.dam2.cine.dto.request.EntradaRequestDTO;
import com.dam2.cine.dto.response.EntradaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface EntradaMapper {

    // TO ENTITY: Todo se ignora porque la entrada se genera 100% en el Servicio
    // al procesar la venta.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "codigo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "venta", ignore = true)
    @Mapping(target = "funcion", ignore = true)
    Entrada toEntity(EntradaRequestDTO dto);

    /*
     * TO DTO:
     * 1. Navegamos profundo para sacar info del ticket (Peli, Sala, Precio).
     * 2. Usamos 'qualifiedByName' para llamar a los métodos de abajo
     * que separan el LocalDateTime en Fecha y Hora.
     */
    @Mapping(source = "estado", target = "estado") 
    @Mapping(source = "funcion.pelicula.titulo", target = "peliculaTitulo")
    @Mapping(source = "funcion.sala.nombre", target = "salaNombre")
    @Mapping(source = "funcion.precio", target = "precio")
    @Mapping(source = "funcion.fechaHora", target = "fecha", qualifiedByName = "extraerFecha")
    @Mapping(source = "funcion.fechaHora", target = "hora", qualifiedByName = "extraerHora")
    EntradaResponseDTO toDTO(Entrada entity);

    List<EntradaResponseDTO> toDTOList(List<Entrada> entradas);

    // --- MÉTODOS AUXILIARES (Java puro) ---
    
    @Named("extraerFecha")
    default LocalDate extraerFecha(LocalDateTime fechaHora) {
        return fechaHora != null ? fechaHora.toLocalDate() : null;
    }

    @Named("extraerHora")
    default LocalTime extraerHora(LocalDateTime fechaHora) {
        return fechaHora != null ? fechaHora.toLocalTime() : null;
    }
}