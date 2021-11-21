package com.salesianos.triana.realestate.v2.interesa.model;

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

}
