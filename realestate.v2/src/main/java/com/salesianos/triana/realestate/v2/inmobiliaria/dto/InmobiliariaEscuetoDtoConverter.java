package com.salesianos.triana.realestate.v2.inmobiliaria.dto;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import org.springframework.stereotype.Component;

@Component
public class InmobiliariaEscuetoDtoConverter {

    public InmobiliariaEscuetoDto inmobiliariaToInmobiliariaEscuetoDto(Inmobiliaria i){
        return InmobiliariaEscuetoDto.builder()
                .id(i.getId())
                .nombre(i.getNombre())
                .build();
    }
}
// No me borres plox