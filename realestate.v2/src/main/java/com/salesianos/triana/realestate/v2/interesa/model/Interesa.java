package com.salesianos.triana.realestate.v2.interesa.model;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter
@Builder

@Table (name = "intereses")
@EntityListeners(AuditingEntityListener.class)
public class Interesa implements Serializable {

    @Builder.Default
    @EmbeddedId
    private InteresaPK id = new InteresaPK();

    @Lob
    private String mensaje;
    @CreatedDate
    private LocalDate createDate;

    // ASOCIACIONES

    @ManyToOne
    @MapsId("vivienda_id")
    @JoinColumn(name = "vivienda_id")
    private Vivienda vivienda;

    @ManyToOne
    @MapsId("usuario_id")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // HELPERS

    // Usuario y Viviendas
    public void addInteres(Vivienda v, Usuario u) {
        v.getListIntereses().add(this);
        u.getListIntereses().add(this);
        this.vivienda=v;
        this.usuario=u;
    }

    public void removeInteres(Vivienda v, Usuario u) {
        v.getListIntereses().remove(this);
        u.getListIntereses().remove(this);
        this.vivienda=null;
        this.usuario=null;
    }

}
