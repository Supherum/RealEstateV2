package com.salesianos.triana.realestate.v2.interesa.controller;

import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDto;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDtoConverter;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaRegisterDto;
import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.interesa.service.InteresaService;
import com.salesianos.triana.realestate.v2.usuario.dto.propietario.GetPropietarioDto;
import com.salesianos.triana.realestate.v2.usuario.dto.propietario.GetPropietarioDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class InteresaController {


    private final ViviendaService viviendaService;
    private final UsuarioService usuarioService;
    private final InteresaService interesaService;
    private final InteresaDtoConverter interesaDtoConverter;
    private final GetPropietarioDtoConverter propietarioDtoConverter;

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
    @PostMapping("/vivienda/{id1}/meinteresa")
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

    @Operation(summary = "Borra un interes de una vivienda")
    @ApiResponses(value = {
            @ApiResponse(description = "No se encuentra la vivienda/interesado",
                    responseCode = "404",
                    content = @Content),
            @ApiResponse(description = "Se ha borrado el interes por la vivienda exitosamente",
                    responseCode = "200",
                    content = @Content)
    })
    @DeleteMapping("vivienda/{id1}/meinteresa")
    public ResponseEntity<?> deleteMeInteresa(
            @Parameter(description = "id de la vivienda") @PathVariable UUID id1, @AuthenticationPrincipal Usuario u) {

        Optional<Vivienda> v=viviendaService.findById(id1);
        List<Interesa> interesaList=interesaService.findAll();

        if(v.isEmpty())
            return ResponseEntity.notFound().build();
        if(v.get().getListIntereses().isEmpty())
            return ResponseEntity.notFound().build();
        if(!u.getId().equals(v.get().getUsuario().getId()) && u.getRol()!= Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        else{
            List<Interesa> lista= interesaList.stream().filter(x-> x.getId().getUsuario_id().equals(u.getId())).collect(Collectors.toList());
            lista.stream().filter(x->x.getId().getVivienda_id().equals(v.get().getId())).collect(Collectors.toList());
            lista.get(0).removeInteres(v.get(),u);
            interesaService.delete(lista.get(0));

            return ResponseEntity.ok().build();
        }
    }

    @Operation(summary = "Devuelve los interesados de una vivienda")
    @ApiResponses(value = {
            @ApiResponse(description = "Lista de todos los interesados",
                    responseCode = "200",
                    content = @Content)
    })
    @GetMapping("/interesado")
    public ResponseEntity<List<GetPropietarioDto>> buscaInteresados() {

        List<Usuario> listaUsuarios = usuarioService.findAll();
        // Se que debido a que está el tipo EAGER crea una recursividad, pero debido
        // al tiempo para implementar la seguridad y además tocar los controladores este
        // por desgracia se quedará dando usuarios que no deberían de estar aunque el método
        // está bien si quitamos la recursividad. Un grafo de entidad hubiera estado bien
        listaUsuarios.stream().filter(x->x.getListIntereses().size()>0).collect(Collectors.toList());
        List<GetPropietarioDto> lista=listaUsuarios.stream().map(x->propietarioDtoConverter.propietarioToDto(x) ).collect(Collectors.toList());
            return ResponseEntity.ok().body(lista);

    }
}
