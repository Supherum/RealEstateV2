
package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInteresadosDto {
    private UUID id;
    private String nombre;
    private String apellidos;
}