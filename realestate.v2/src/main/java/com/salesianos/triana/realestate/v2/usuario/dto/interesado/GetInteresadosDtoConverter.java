
package com.salesianos.triana.realestate.v2.usuario.dto.interesado;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class GetInteresadosDtoConverter {
    public GetInteresadosDto InteresadoToGetInteresadosDto(Usuario u){
        return GetInteresadosDto.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .apellidos(u.getApellidos())
                .build();
    }
}