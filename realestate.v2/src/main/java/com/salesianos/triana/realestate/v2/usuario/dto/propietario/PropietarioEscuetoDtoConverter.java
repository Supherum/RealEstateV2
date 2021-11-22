package com.salesianos.triana.realestate.v2.usuario.dto.propietario;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class PropietarioEscuetoDtoConverter {

    public PropietarioEscuetoDto propietarioToPropietarioEscuetoDto(Usuario p){
        return PropietarioEscuetoDto.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .apellido(p.getApellidos())
                .build();
    }

}
