package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.shared.dto.PropietarioViendaDto;
import com.salesianos.triana.realestate.v2.shared.dto.PropietarioViviendaDtoConverter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Log
@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final GetPropietarioDtoConverter getPropietarioDtoConverter;
    private final PropietarioEscuetoDtoConverter propietarioEscuetoDtoConverter;
    private final PropietarioViviendaDtoConverter propietarioViviendaDtoConverter;

    @GetMapping("/propietario/")
    public ResponseEntity<List<Usuario>> findPropietarios (){

        return ResponseEntity.ok(usuarioService.findPropietarios());
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

}
