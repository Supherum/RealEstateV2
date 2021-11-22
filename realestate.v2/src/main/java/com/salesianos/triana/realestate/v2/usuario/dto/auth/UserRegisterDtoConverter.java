package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import com.salesianos.triana.realestate.v2.shared.security.PasswordCodifier;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterDtoConverter {

    private final PasswordCodifier codificador;

    public Usuario UserLoginDtoToUser (UserRegisterDto dto){
        return Usuario.builder()
                .apellidos(dto.getApellidos())
                .email(dto.getEmail())
                .nombre(dto.getNombre())
                .nick(dto.getNick())
                .password(codificador.passwordEncoder().encode(dto.getPassword()))
                .build();
    }

    public UserRegisterSusscesDto UserToUserSussces (Usuario u){
        return UserRegisterSusscesDto.builder()
                .role(u.getRol())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .nick(u.getNick())
                .email(u.getEmail())
                .role(Rol.Propietario)
                .build();
    }
}
