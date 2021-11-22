package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaListaDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InmobiliariaViviendasDto {

    private String nombre, email, telefono;
    private UUID id;

    private List<ViviendaListaDto> viviendas;
}