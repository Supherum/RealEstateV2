package com.salesianos.triana.realestate.v2.vivienda.dto;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailViviendaDto {

    private UUID id;

    private String titulo, direccion, avatar;
}
