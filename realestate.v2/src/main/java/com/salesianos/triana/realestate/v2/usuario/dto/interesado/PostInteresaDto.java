package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class PostInteresaDto {

    private String nombre, apellidos, direccion, email, telefono, avatar, mensaje;

}
