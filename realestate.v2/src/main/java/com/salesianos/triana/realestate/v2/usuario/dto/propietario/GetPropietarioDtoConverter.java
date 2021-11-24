package com.salesianos.triana.realestate.v2.usuario.dto.propietario;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.stereotype.Component;


@Component
public class GetPropietarioDtoConverter {

    public GetPropietarioDto propietarioToDto(Usuario u){
        return GetPropietarioDto.builder()
                .id(u.getId())
                .nick(u.getNick())
                .rol(u.getRol())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .avatar(u.getAvatar())
                .direccion(u.getDireccion())
                .email(u.getEmail())
                .telefono(u.getTelefono())
                .build();
    }

    public Usuario dtoToPropietario(GetPropietarioDto pdto){
        return Usuario.builder()
                .id(pdto.getId())
                .nombre(pdto.getNombre())
                .apellidos(pdto.getApellidos())
                .avatar(pdto.getAvatar())
                .direccion(pdto.getDireccion())
                .email(pdto.getEmail())
                .telefono(pdto.getTelefono())
                .nick(pdto.getNick())
                .rol(pdto.getRol())
                .build();
    }
}
