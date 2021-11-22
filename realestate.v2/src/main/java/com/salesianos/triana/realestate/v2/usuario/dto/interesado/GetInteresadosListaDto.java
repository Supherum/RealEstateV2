
package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class GetInteresadosListaDto {

    private UUID id;
    private String nombre, apellidos, direccion, email, telefono, avatar;

}