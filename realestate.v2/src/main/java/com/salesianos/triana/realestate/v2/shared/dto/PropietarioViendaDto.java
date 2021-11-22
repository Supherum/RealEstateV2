package com.salesianos.triana.realestate.v2.shared.dto;

import com.salesianos.triana.realestate.v2.vivienda.dto.DetailViviendaDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PropietarioViendaDto {

    // Propietario
    private UUID id;

    private String nombre,apellidos,direccionPropietario,email,telefono,avatarPropietario;

    // Vivienda
    private List<DetailViviendaDto> vivienda;
}
