package com.salesianos.triana.realestate.v2.shared.dto;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.dto.DetailViviendaDto;
import org.springframework.stereotype.Component;

@Component
public class PropietarioViviendaDtoConverter {
    public PropietarioViendaDto propietarioToPropietarioVviendaDto(Usuario p){
    return PropietarioViendaDto.builder()
            .id(p.getId())
            .nombre(p.getNombre())
            .apellidos(p.getApellidos())
            .direccionPropietario(p.getDireccion())
            .email(p.getEmail())
            .telefono(p.getTelefono())
            .avatarPropietario(p.getAvatar())
            .vivienda(p.getListVivienda().stream().map(v->
                    new DetailViviendaDto(v.getId(),v.getTitulo(),v.getDireccion(),v.getAvatar())).toList())
            .build();
    }
}
