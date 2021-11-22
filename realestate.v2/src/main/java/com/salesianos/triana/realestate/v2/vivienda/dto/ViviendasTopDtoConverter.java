package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendasTopDtoConverter {

    public ViviendasTopDto viviendaToViviendasTopDto(Vivienda v) {
        return ViviendasTopDto.builder()
                .id(v.getId())
                .tipo(v.getTipo())
                .titulo(v.getTitulo())
                .direccion(v.getDireccion())
                .provincia(v.getProvincia())
                .poblacion(v.getPoblacion())
                .avatar(v.getAvatar())
                .precio(v.getPrecio())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numeroBanos(v.getNumBanos())
                .numeroHabitaciones(v.getNumHabitaciones())
                .numeroInteresados(v.getListIntereses().size())
                .build();
    }

}
