package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UserLoginResponseDto {

    private String nick;
    private String nombre;
    private String apellidos;
    private String rol;
    private String tokenJwt;
}
