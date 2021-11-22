package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDetalleDtoConverter {

    public ViviendaDetalleDto viviendaToDetalleDto (Vivienda v){
        return ViviendaDetalleDto.builder()
                .titulo(v.getTitulo())
                .direccion(v.getDireccion())
                .avatar(v.getAvatar())
                .latLng(v.getLatLng())
                .descripcion(v.getDescripcion())
                .codPostal(v.getCodPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tienePiscina(v.getTienePiscina())
                .tieneAscensor(v.getTieneAscensor())
                .tieneGaraje(v.getTieneGaraje())
                .numHabitaciones(v.getNumHabitaciones())
                .numBanos(v.getNumBanos())
                .precio(v.getPrecio())
                .tipo(v.getTipo())
                .metrosCuadrados(v.getMetrosCuadrados())
                .build();
    }
}
