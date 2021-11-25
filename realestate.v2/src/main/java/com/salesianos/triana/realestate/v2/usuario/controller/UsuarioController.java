package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.shared.dto.PropietarioViendaDto;
import com.salesianos.triana.realestate.v2.shared.dto.PropietarioViviendaDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.dto.propietario.GetPropietarioDto;
import com.salesianos.triana.realestate.v2.usuario.dto.propietario.GetPropietarioDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.dto.propietario.PropietarioEscuetoDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final GetPropietarioDtoConverter getPropietarioDtoConverter;
    private final PropietarioEscuetoDtoConverter propietarioEscuetoDtoConverter;
    private final PropietarioViviendaDtoConverter propietarioViviendaDtoConverter;
    private final InmobiliariaService inmobiliariaService;

    @GetMapping("/propietario/")
    public ResponseEntity<List<GetPropietarioDto>> findPropietarios (){

        return ResponseEntity.ok(usuarioService.findPropietarios().stream().map(x->getPropietarioDtoConverter.propietarioToDto(x)).collect(Collectors.toList()));
    }

    @GetMapping("/gestores/")
    public ResponseEntity<List<GetPropietarioDto>> findGestores (){

        return ResponseEntity.ok(usuarioService.findGestores().stream().map(x->getPropietarioDtoConverter.propietarioToDto(x)).collect(Collectors.toList()));
    }

    @Operation(summary = "Devuelve los detalles de un propietario y su lista de viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el propietario",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra el propietario",
                    content = @Content)
    })
    @GetMapping("/propietario/detalle/{id}")
    public ResponseEntity <?> getDetailPropietario(@PathVariable("id") UUID id, @AuthenticationPrincipal Usuario u){

        Optional<Usuario> p = usuarioService.findById(id);

        if(p.isEmpty())
            return ResponseEntity.notFound().build();
        if(!u.getId().equals(id) && u.getRol()!=Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else{
            PropietarioViendaDto propietarioViendaDto = propietarioViviendaDtoConverter.propietarioToPropietarioVviendaDto(p.get());
            return ResponseEntity.ok().body(propietarioViendaDto);
        }
    }


    @Operation(summary = "Borra un propietario y sus viviendas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado con exito",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra el propietario",
                    content = @Content)
    })
    @DeleteMapping("/propietario/{id}")
    public ResponseEntity<?> deletePropietario(@PathVariable("id")UUID idProp, @AuthenticationPrincipal Usuario u) {

        Optional<Usuario> p = usuarioService.findById(idProp);

        if(p.isEmpty())
            return ResponseEntity.notFound().build();
        if(!u.getId().equals(idProp) && u.getRol()!=Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        else{
            usuarioService.deleteById(idProp);
            return ResponseEntity.ok().build();
        }

    }


    @Operation(summary = "Borra un Gestor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha borrado con exito",
                    content = { @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "No se encuentra el propietario",
                    content = @Content)
    })
    @DeleteMapping("/inmobiliaria/{id}/gestor/")
    public ResponseEntity<?> deleteGestor(@PathVariable("id")UUID id, @AuthenticationPrincipal Usuario u) {

        Optional<Usuario> p = usuarioService.findById(id);
        Boolean isGestor=false;

        if(p.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Inmobiliaria> i= inmobiliariaService.findById(p.get().getInmobiliaria().getId());

        if(p.get().getRol()!=Rol.Gestor)
            return ResponseEntity.badRequest().build();

        if(u.getInmobiliaria()!=null){
            if(i.get().getId().equals(u.getInmobiliaria().getId()))
                isGestor=true;
        }

        if(!isGestor && u.getRol()!=Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        else{
            usuarioService.delete(p.get());
            return ResponseEntity.ok().build();
        }

    }



}
