package com.dam2.cine.mapper;

import com.dam2.cine.model.Usuario;
import com.dam2.cine.dto.request.UsuarioRequestDTO;
import com.dam2.cine.dto.response.UsuarioResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    /*
     * TO ENTITY:
     * El DTO trae 'rolId' (un número).
     * El Mapper NO puede convertir un número en un objeto Rol.
     * Por eso IGNORAMOS 'rol'. Será el SERVICIO quien busque el Rol en la BD
     * y lo asigne manualmente.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rol", ignore = true) 
    @Mapping(target = "ventas", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);

    /*
     * TO DTO:
     * Queremos mostrar el nombre del rol ("ROLE_ADMIN"), no el objeto entero.
     * Navegamos: Entidad Usuario -> getRol() -> getNombre().
     */
    @Mapping(source = "rol.nombre", target = "rol") 
    UsuarioResponseDTO toDTO(Usuario entity);
}