package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.stereotype.Component;


@Component
public class GetInteresadosListaDtoConverter {

    public GetInteresadosListaDto interesadoToGetInteresadosListaDto(Usuario u) {
        return GetInteresadosListaDto.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .direccion(u.getDireccion())
                .email(u.getEmail())
                .telefono(u.getTelefono())
                .avatar(u.getAvatar())
                .build();
    }

}