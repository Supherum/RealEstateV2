package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ViviendaListaDto {

    private UUID id;
    private String titulo;
    private Type tipo;
    private String direccion,provincia,poblacion,avatar;
    private float precio;
    private double metrosCuadrados;
    private int numeroBanos;
    private int numeroHabitaciones;

}
