package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InmobiliariaViviendasDtoConverter {



    public InmobiliariaViviendasDto inmobiliriaToInmoViviDto(Inmobiliaria i) {
        return InmobiliariaViviendasDto.builder()
                .nombre(i.getNombre())
                .id(i.getId())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .viviendas(i.getListaViviendas().stream().map(v -> new ViviendaListaDto(v.getId(), v.getTitulo(), v.getTipo(),
                        v.getDireccion(), v.getProvincia(), v.getPoblacion(), v.getAvatar(), v.getPrecio(),
                        v.getMetrosCuadrados(), v.getNumBanos(), v.getNumHabitaciones())).toList())
                .build();
    }
}