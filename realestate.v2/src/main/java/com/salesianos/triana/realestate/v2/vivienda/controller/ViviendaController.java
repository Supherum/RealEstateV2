package com.salesianos.triana.realestate.v2.vivienda.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.interesa.service.InteresaService;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import com.salesianos.triana.realestate.v2.vivienda.dto.*;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
@Tag(name = "Controlador de los vivienda")

public class ViviendaController {

    private final ViviendaService viviendaService;
    private final UsuarioService propietarioService;
    private final InmobiliariaService inmobiliariaService;
    private final InteresaService interesaService;

    private final ViviendaPropietarioConverterDto viviendaPropietarioConverterDto;
    private final ViviendaDetalleDtoConverter viviendaDetalleDtoConverter;
    private final ViviendaListaDtoConverter viviendaListaDtoConverter;
    private final ViviendasTopDtoConverter viviendasTopDtoConverter;
    private final InteresaDtoConverter interesaDtoConverter;


    @Operation(summary = "Creas una vivienda y un propietario o lo rescatas de la base de datos (el propietario)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado correctamente",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400",
                    description = "Datos incorrectos",
                    content = @Content)
    })

    @PostMapping ("/")
    public ResponseEntity<ViviendaDetalleDto> addVivienda(@RequestBody ViviendaPropietarioDto dto){
        Vivienda v=viviendaPropietarioConverterDto.getVivienda(dto);
        Usuario p= viviendaPropietarioConverterDto.getPropietario(dto);
        if(v.getProvincia()==null|| v.getCodPostal()==null || v.getDireccion()==null||
                p.getNick()==null ||  !dto.getPassword().contentEquals(dto.getPassword2()) ||
                v.getDescripcion()==null|| v.getPoblacion()==null || v.getTipo()==null || v.getTitulo()==null)
            return ResponseEntity.badRequest().build();
        if(p.getId()!=null)
            p=  propietarioService.findById(p.getId()).get();
        propietarioService.save(p);
        v.addUsuarioToVivienda(p);
        viviendaService.save(v);
        return ResponseEntity.status(HttpStatus.CREATED).body(viviendaDetalleDtoConverter.viviendaToDetalleDto(v));
    }

    @Operation(summary = "Devuelve la lista de viviendas paginada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se han encontrado las 9 primeras viviendas por defecto",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la lista de viviendas",
                    content = @Content)
    })
    @GetMapping ("/")
    public ResponseEntity<?> getAllViviendas ( @PageableDefault(size = 9,page = 0) Pageable pageable){
        Page<Vivienda> lista=viviendaService.findAll(pageable);
        if(lista.isEmpty())
            return ResponseEntity.notFound().build();
        Page<ViviendaListaDto> dtoLista= lista.map(x->viviendaListaDtoConverter.viviendaToViviendaListaDto(x));
        return ResponseEntity.ok(dtoLista);
    }

    @Operation(summary = "Devuelve el detalle en forma de Dto de un propietario y su vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado la vivienda y su propietario",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda o el propietario",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ViviendaPropietarioDto> getDetallesViviendasPropietario(@PathVariable ("id") UUID id){
        final Optional<Vivienda> v=viviendaService.findById(id);
        log.info(id.toString());

        if(v.isEmpty() || v.get().getUsuario()==null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(viviendaPropietarioConverterDto.viviendaToViviendaPropietarioDto(viviendaService.findById(id).get()));
    }

    @Operation(summary = "Actualiza los datos de una vivienda")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha actualizado correctamente",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ViviendaDetalleDto> putVivienda(@PathVariable UUID id, @RequestBody ViviendaDetalleDto dto, @AuthenticationPrincipal Usuario u) {


        if(viviendaService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        if(!u.getId().equals(viviendaService.findById(id).get().getUsuario().getId()) && u.getRol()!= Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else{
        return ResponseEntity.of(viviendaService.findById(id).map(v->{
            v.setTitulo(dto.getTitulo());
            v.setDescripcion(dto.getDescripcion());
            v.setAvatar(dto.getAvatar());
            v.setLatLng(dto.getLatLng());
            v.setDireccion(dto.getDireccion());
            v.setCodPostal(dto.getCodPostal());
            v.setProvincia(dto.getProvincia());
            v.setPoblacion(dto.getPoblacion());
            v.setTienePiscina(dto.getTienePiscina());
            v.setTieneGaraje(dto.getTieneGaraje());
            v.setTieneAscensor(dto.getTieneAscensor());
            v.setPrecio(dto.getPrecio());
            v.setMetrosCuadrados(dto.getMetrosCuadrados());
            v.setNumHabitaciones(dto.getNumHabitaciones());
            v.setNumBanos(dto.getNumBanos());
            v.setTipo(dto.getTipo());
            viviendaService.save(v);
            return viviendaDetalleDtoConverter.viviendaToDetalleDto(v);
        }));
        }
    }

    @Operation(summary = "Vinculas una vivienda con una inmobiliaria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado correctamente",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra la vivienda",
                    content = @Content)
    })
    @PostMapping("/{id}/inmobiliaria/{id2}")
    public ResponseEntity<?> asociarViviendaInmobiliaria (@PathVariable ("id") UUID id1, @PathVariable("id2") UUID id2, @AuthenticationPrincipal Usuario u){
        Optional<Inmobiliaria> i=inmobiliariaService.findById(id2);
        Optional<Vivienda> v=viviendaService.findById(id1);

        if(i.isEmpty() || v.isEmpty())
            return ResponseEntity.notFound().build();
        if(!u.getId().equals(v.get().getUsuario().getId()) && u.getRol()!= Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else{
            v.get().addInmobiliariaToVivienda(i.get());
            viviendaService.edit(v.get());
            inmobiliariaService.edit(i.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }
}

