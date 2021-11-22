package com.salesianos.triana.realestate.v2.inmobiliaria.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class InmobiliariaEscuetoDto {
    private UUID id;
    private String nombre;
}
// No me borres plox
