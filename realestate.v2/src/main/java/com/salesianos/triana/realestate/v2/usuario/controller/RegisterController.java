package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterSusscesDto;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final UserRegisterDtoConverter userRegisterDtoConverter;
    private final InmobiliariaService inmobiliariaService;


    @PostMapping("/auth/register/propietario")
    public ResponseEntity<UserRegisterSusscesDto> nuevoPropietario (@RequestBody UserRegisterDto dto){

        Usuario u= authService.saveUsuario(dto, Rol.Propietario);
        if(u==null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterDtoConverter.UserToUserSussces(u));
        }
    }

    @PostMapping("/auth/register/gestor")
    public ResponseEntity<UserRegisterSusscesDto> nuevoGestor (@RequestBody UserRegisterDto dto){

        Usuario u= authService.saveUsuario(dto,Rol.Gestor);
        if(u==null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterDtoConverter.UserToUserSussces(u));
        }
    }

    @PostMapping("/auth/register/administrador")
    public ResponseEntity<UserRegisterSusscesDto> nuevoAdmin (@RequestBody UserRegisterDto dto){

        Usuario u= authService.saveUsuario(dto,Rol.Administrador);
        if(u==null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterDtoConverter.UserToUserSussces(u));
        }
    }

    @PostMapping("/inmobiliaria/{id}/gestor")
    public ResponseEntity<UserRegisterSusscesDto> nuevoGestorChulo (@RequestBody UserRegisterDto dto,
                                                              @PathVariable ("id") UUID id,
                                                              @AuthenticationPrincipal Usuario p){

        Boolean isGestor=false;
        Optional<Inmobiliaria> i= inmobiliariaService.findById(id);

        if(i.isEmpty())
            return  ResponseEntity.notFound().build();

        if(p.getInmobiliaria()!=null){
            if(i.get().getId().equals(p.getInmobiliaria().getId()))
                isGestor=true;
        }

        if(!isGestor && p.getRol()!=Rol.Administrador)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        Usuario u= authService.saveGestorConInmobiliaria(dto,Rol.Gestor,i.get());
        if(u==null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterDtoConverter.UserToUserSussces(u));
        }
    }
}
