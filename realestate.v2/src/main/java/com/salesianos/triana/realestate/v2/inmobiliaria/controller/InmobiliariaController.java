package com.salesianos.triana.realestate.v2.inmobiliaria.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.dto.GetInmobiliariasDto;
import com.salesianos.triana.realestate.v2.inmobiliaria.dto.GetInmobiliasDtoConverter;
import com.salesianos.triana.realestate.v2.inmobiliaria.dto.InmobiliariaEscuetoDtoConverter;
import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.vivienda.dto.InmobiliariaViviendasDto;
import com.salesianos.triana.realestate.v2.vivienda.dto.InmobiliariaViviendasDtoConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inmobiliaria")
@Tag(name = "Controlador de los inmobiliaria")
public class InmobiliariaController {

    private final InmobiliariaService inmobiliariaService;
    private final GetInmobiliasDtoConverter getInmobiliasDtoConverter;
    private final InmobiliariaEscuetoDtoConverter inmobiliariaEscuetoDtoConverter;
    private final InmobiliariaViviendasDtoConverter inmobiliariaViviendasDtoConverter;


    @Operation(summary = "Crea una nueva inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500",
                    description = "No se ha podido crear la inmobiliaria",
                    content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Inmobiliaria> addInmo(@RequestBody GetInmobiliariasDto i){
        if (i.getNombre().isEmpty() || i.getEmail().isEmpty() || i.getTelefono().isEmpty())
            return ResponseEntity.badRequest().build();
        Inmobiliaria nueva = getInmobiliasDtoConverter.dtoToInmobiliaria(i);
        return ResponseEntity.status(HttpStatus.CREATED).body(inmobiliariaService.save(nueva));
    }


    @Operation(summary = "Devuelve la lista de inmobiliarias paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las 9 primeras inmobiliarias por defecto",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la lista de inmobiliarias",
                    content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<?> getListInmobiliaria(@PageableDefault(size = 9 ,page = 0) Pageable p) {
        Page<Inmobiliaria> lista = inmobiliariaService.findAll(p);
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Page<GetInmobiliariasDto> listaDto = lista.map(i -> getInmobiliasDtoConverter.inmobiliariaToInmobiliariasDto(i));
        return ResponseEntity.ok(listaDto);
    }

    @Operation(summary = "Encontrar una inmobiliaria por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(description = "No se encuentra la Inmobiliaria",
                    responseCode = "404",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<InmobiliariaViviendasDto> findByIdInmoVivi(@PathVariable UUID id){
        Optional<Inmobiliaria> i = inmobiliariaService.findById(id);

        if (i.isEmpty())
            return ResponseEntity.notFound().build();
        InmobiliariaViviendasDto inmoVivi = inmobiliariaViviendasDtoConverter.inmobiliriaToInmoViviDto(i.get());

        return ResponseEntity.ok().body(inmoVivi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeInmo(@PathVariable UUID id){
        if (inmobiliariaService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }else {

            Inmobiliaria i = inmobiliariaService.findById(id).get();
            //i.getViviendas().stream().map(v -> v.removeToInmobiliaria(i)).collect(Collectors.toList());

            inmobiliariaService.delete(i);

            return ResponseEntity.ok().build();
        }
    }


}
