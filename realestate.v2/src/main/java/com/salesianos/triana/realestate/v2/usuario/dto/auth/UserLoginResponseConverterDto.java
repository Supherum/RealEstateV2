package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UserLoginResponseConverterDto {

    public UserLoginResponseDto UserAndJwtToResponse (Usuario u, String jwt){
        return UserLoginResponseDto.builder()
                .nick(u.getNick())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .rol(u.getRol().name())
                .tokenJwt(jwt)
                .build();
    }
}
