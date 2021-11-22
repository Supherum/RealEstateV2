package com.salesianos.triana.realestate.v2.interesa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class InteresaPK implements Serializable {

    private Long vivienda_id;
    private Long usuario_id;
}
