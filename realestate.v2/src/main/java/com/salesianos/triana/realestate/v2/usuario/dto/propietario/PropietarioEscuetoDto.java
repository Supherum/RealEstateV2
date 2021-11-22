package com.salesianos.triana.realestate.v2.usuario.dto.propietario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class PropietarioEscuetoDto {
    private UUID id;
    private String nombre;
    private String apellido;
}
