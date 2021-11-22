package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor @Builder
@Getter @Setter
public class ViviendasTopDto {

    private UUID id;
    private Type tipo;
    private String titulo, direccion, provincia, poblacion, avatar;
    private float precio;
    private double metrosCuadrados;
    private int numeroBanos;
    private int numeroHabitaciones;
    private int numeroInteresados;

}
