package com.salesianos.triana.realestate.v2.inmobiliaria.dto;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetInmobiliariasDto {

    private String nombre, email, telefono;
    private UUID id;
}
