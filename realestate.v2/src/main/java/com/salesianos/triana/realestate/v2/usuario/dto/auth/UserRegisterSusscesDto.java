package com.salesianos.triana.realestate.v2.usuario.dto.auth;

import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class UserRegisterSusscesDto {

    private String nick;
    private String nombre;
    private String apellidos;
    private String email;
    private Rol role;
}
