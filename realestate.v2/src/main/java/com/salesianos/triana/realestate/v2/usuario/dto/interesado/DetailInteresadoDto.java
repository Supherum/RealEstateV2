package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaListaDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class DetailInteresadoDto {

    private String nombre, apellidos, direccion, email, telefono, avatar;
    private List<ViviendaListaDto> viviendas;

}
