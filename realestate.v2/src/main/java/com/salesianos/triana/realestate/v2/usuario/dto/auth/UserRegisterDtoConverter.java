package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterDtoConverter {

    private final PasswordEncoder codificador;

    public Usuario UserLoginDtoToUser (UserRegisterDto dto,Rol rol){
        return Usuario.builder()
                .apellidos(dto.getApellidos())
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .nick(dto.getNick())
                .password(codificador.encode(dto.getPassword()))
                .rol(rol)
                .build();
    }

    public UserRegisterSusscesDto UserToUserSussces (Usuario u){
        return UserRegisterSusscesDto.builder()
                .role(u.getRol())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .nick(u.getNick())
                .email(u.getEmail())
                .role(u.getRol())
                .build();
    }
}
