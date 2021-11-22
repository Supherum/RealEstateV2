package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaListaDtoConverter {

    public ViviendaListaDto viviendaToViviendaListaDto(Vivienda v){
       return ViviendaListaDto.builder()
               .titulo(v.getTitulo())
               .precio(v.getPrecio())
               .metrosCuadrados(v.getMetrosCuadrados())
               .numeroBanos(v.getNumBanos())
               .numeroHabitaciones(v.getNumHabitaciones())
               .tipo(v.getTipo())
               .direccion(v.getDireccion())
               .poblacion(v.getPoblacion())
               .provincia(v.getProvincia())
               .id(v.getId())
               .avatar(v.getAvatar())
               .build();
    }
}
