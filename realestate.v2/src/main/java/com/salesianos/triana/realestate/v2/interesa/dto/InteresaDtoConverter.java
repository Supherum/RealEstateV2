package com.salesianos.triana.realestate.v2.interesa.dto;

import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
public class InteresaDtoConverter {
    public Interesa InteresaDtoToInteresa (InteresaDto i){
        return Interesa.builder()
                .mensaje(i.getMensaje())
                .createDate(LocalDateTime.now())
                .build();
    }

    public InteresaDto InteresaToInteresaDto(Interesa i){
        return InteresaDto.builder()
                .mensaje(i.getMensaje())
                .createDate(LocalDateTime.now())
                .build();
    }

    public Interesa interesaRegisterToInteresa(InteresaRegisterDto i){
        return Interesa.builder()
                .mensaje(i.getMensaje())
                .createDate(LocalDateTime.now())
        .build();
    }
}