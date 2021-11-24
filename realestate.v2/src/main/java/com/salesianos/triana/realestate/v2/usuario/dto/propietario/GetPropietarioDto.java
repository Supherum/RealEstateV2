package com.salesianos.triana.realestate.v2.usuario.dto.propietario;

import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPropietarioDto {
    private UUID id;
    private String nick;
    private Rol rol;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String email;
    private String telefono;
    private String avatar;
}
