package com.salesianos.triana.realestate.v2.inmobiliaria.model;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder

@Table(name = "inmobiliarias")
@EntityListeners(AuditingEntityListener.class)
public class Inmobiliaria implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )

    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String nombre, email, telefono;



    // ASOCIACIONES

    @OneToMany (mappedBy = "inmobiliaria")
    @Builder.Default
    private List<Vivienda> listaViviendas=new ArrayList<>();

    @OneToMany (mappedBy = "inmobiliaria")
    @Builder.Default
    private List<Usuario> listaUsuarios=new ArrayList<>();

}
