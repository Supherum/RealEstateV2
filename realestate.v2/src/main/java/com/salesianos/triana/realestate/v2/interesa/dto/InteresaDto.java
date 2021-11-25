package com.salesianos.triana.realestate.v2.interesa.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class InteresaDto {
    private String mensaje;
    private LocalDateTime createDate;
}
