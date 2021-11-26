package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class PasswordChangeDto {

    private String password1;
    private String password2;
}
