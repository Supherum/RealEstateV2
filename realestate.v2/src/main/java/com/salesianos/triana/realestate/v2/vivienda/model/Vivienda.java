package com.salesianos.triana.realestate.v2.vivienda.model;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
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

@Table (name = "viviendas")
@EntityListeners(AuditingEntityListener.class)
public class Vivienda implements Serializable {

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

    private String titulo, descripcion, avatar, latLng, direccion, codPostal, poblacion, provincia;
    private Boolean tienePiscina, tieneAscensor, tieneGaraje;
    private Integer numHabitaciones, numBanos;
    private Float precio;
    private Double metrosCuadrados;

    @Enumerated (EnumType.STRING)
    private Type tipo;

    // ASOCIACIONES

    @ManyToOne
    private Inmobiliaria inmobiliaria;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "vivienda")
    @Builder.Default
    private List<Interesa> listIntereses=new ArrayList<>();



    // HELPERS

    // Inmobiliaria
    public void addInmobiliariaToVivienda(Inmobiliaria i){
        this.inmobiliaria=i;
        i.getListaViviendas().add(this);
    }
    public void remoceInmobiliariaToVivienda(Inmobiliaria i){
        this.inmobiliaria=null;
        i.getListaViviendas().remove(this);
    }

    // Vivienda
    public void addUsuarioToVivienda(Usuario u){
        this.usuario=u;
        u.getListVivienda().add(this);
    }


}
