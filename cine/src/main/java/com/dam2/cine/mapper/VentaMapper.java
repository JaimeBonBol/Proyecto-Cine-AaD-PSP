package com.dam2.cine.mapper;

import com.dam2.cine.model.Venta;
import com.dam2.cine.dto.request.VentaRequestDTO;
import com.dam2.cine.dto.response.VentaResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EntradaMapper.class})
public interface VentaMapper {

    /* * MAPPINGS DE ENTRADA (Creación)
     * ------------------------------
     * @Mapping(target = "estado", ignore = true)
     * * ¿POR QUÉ LO IGNORAMOS?
     * Porque en tu 'VentaRequestDTO' NO tienes un campo estado (y es correcto que no esté).
     * El estado inicial ("PENDIENTE" o "PAGADA") se decide en la lógica del Servicio,
     * no lo decide el usuario que envía el formulario.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fecha", ignore = true)       
    @Mapping(target = "total", ignore = true)       
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "metodoPago", ignore = true)  
    @Mapping(target = "usuario", ignore = true)     
    @Mapping(target = "entradas", ignore = true)    
    Venta toEntity(VentaRequestDTO dto);

    /* * MAPPINGS DE SALIDA (Respuesta)
     * ------------------------------
     * Aquí NO hace falta poner nada para 'estado'.
     * * ¿POR QUÉ?
     * Entidad: tiene "String estado"
     * ResponseDTO: tiene "String estado"
     * * Como se llaman IDÉNTICOS, MapStruct hace esto solo por detrás:
     * dto.setEstado( entity.getEstado() );
     */
    @Mapping(source = "usuario.email", target = "emailUsuario")
    VentaResponseDTO toDTO(Venta entity);
}