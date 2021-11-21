package com.salesianos.triana.realestate.v2.vivienda.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@Table (name = "viviendas")
@EntityListeners(AuditingEntityListener.class)
public class Vivienda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo, descripcion, avatar, latLng, direccion, codPostal, poblacion, provincia;
    private Boolean tienePiscina, tieneAscensor, tieneGaraje;
    private Integer numHabitaciones, numBanos;
    private Float precio;
    private Double metrosCuadrados;

    @Enumerated (EnumType.STRING)
    private Type tipo;

}
