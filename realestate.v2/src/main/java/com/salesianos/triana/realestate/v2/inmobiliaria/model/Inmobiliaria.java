package com.salesianos.triana.realestate.v2.inmobiliaria.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@Table(name = "inmobiliarias")
@EntityListeners(AuditingEntityListener.class)
public class Inmobiliaria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre, email, telefono;


}
