package com.salesianos.triana.realestate.v2.interesa.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaMeInteresaDtopConverter {

    public ViviendaMeInteresaDtop viviendaToViviendaMeInteresaDto (Vivienda v){
        return ViviendaMeInteresaDtop.builder()
                .titulo(v.getTitulo())
                .id(v.getId())
                .descripcion(v.getDescripcion())
                .interes(false)
                .build();
    }

}
