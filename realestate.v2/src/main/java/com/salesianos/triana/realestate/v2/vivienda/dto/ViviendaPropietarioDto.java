package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ViviendaPropietarioDto {


    // Vivienda
    private String titulo, descripcion, avatar, latLng, direccion, codPostal, poblacion, provincia;
    private Boolean tienePiscina, tieneAscensor, tieneGaraje;
    private Integer numHabitaciones, numBanos;
    private Float precio;
    private Double metrosCuadrados;
    private Type tipo;

    // Propietario
    private UUID id;
    private String nombre,apellidos,direccionPropietario,email,telefono,avatarPropietario,nick,password,password2;

    // Inmobiliaria
    private String inmobiliariaNombre;
}
