package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserLoginDto {

    private String nick;
    private String password;
}
