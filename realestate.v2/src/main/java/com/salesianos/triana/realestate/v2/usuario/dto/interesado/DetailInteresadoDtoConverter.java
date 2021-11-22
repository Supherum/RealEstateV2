package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaListaDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailInteresadoDtoConverter {

    @Autowired
    private ViviendaListaDtoConverter viviendaListaDtoConverter;

    public DetailInteresadoDto interesadoToDetailInteresadoDto(Usuario i){
        return DetailInteresadoDto.builder()
                .nombre(i.getNombre())
                .apellidos(i.getApellidos())
                .direccion(i.getDireccion())
                .email(i.getEmail())
                .telefono(i.getTelefono())
                .avatar(i.getAvatar())
                .viviendas(i.getListIntereses().stream().map(m -> {
                    return viviendaListaDtoConverter.viviendaToViviendaListaDto(m.getVivienda());
                }).toList())
                .build();
    }

}
