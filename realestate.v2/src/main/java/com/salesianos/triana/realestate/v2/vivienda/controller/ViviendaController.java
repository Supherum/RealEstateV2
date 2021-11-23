package com.salesianos.triana.realestate.v2.vivienda.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.interesa.service.InteresaService;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDtoConverter;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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

    // paginado
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
}
