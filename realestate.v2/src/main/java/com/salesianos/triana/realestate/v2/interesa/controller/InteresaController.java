package com.salesianos.triana.realestate.v2.interesa.controller;

import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDto;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDtoConverter;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaRegisterDto;
import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.interesa.service.InteresaService;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class InteresaController {


    private final ViviendaService viviendaService;
    private final UsuarioService usuarioService;
    private final InteresaService interesaService;
    private final InteresaDtoConverter interesaDtoConverter;

    @Operation(summary = "Añade un nuevo interesa con un interesado existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda",
                    content = @Content),
            @ApiResponse(responseCode = "201",
                    description = "Interes creado correctamente",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Interesa.class))
                    }
            )
    })
    @PostMapping("/vivienda/{id1}/meinteresa/")
    public ResponseEntity<InteresaDto> addInteresadoVivienda(@PathVariable("id1") UUID idVivienda,
                                                             @AuthenticationPrincipal Usuario u,
                                                             @RequestBody InteresaRegisterDto iDto){

        if(viviendaService.findById(idVivienda).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Interesa i = interesaDtoConverter.interesaRegisterToInteresa(iDto);
        Optional<Vivienda> vivivenda = viviendaService.findById(idVivienda);
        i.addInteres(vivivenda.get(),u);
        interesaService.save(i);


        return ResponseEntity.ok().body(interesaDtoConverter.InteresaToInteresaDto(i));

    }
}
