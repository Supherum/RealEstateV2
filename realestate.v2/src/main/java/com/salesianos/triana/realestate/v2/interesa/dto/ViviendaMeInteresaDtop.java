package com.salesianos.triana.realestate.v2.interesa.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ViviendaMeInteresaDtop {

    private UUID id;
    private String titulo;
    private String descripcion;
    private Boolean interes;
}
