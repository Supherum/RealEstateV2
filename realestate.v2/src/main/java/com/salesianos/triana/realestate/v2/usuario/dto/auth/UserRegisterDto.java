package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class UserRegisterDto {

    private String nick;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String password2;
}
