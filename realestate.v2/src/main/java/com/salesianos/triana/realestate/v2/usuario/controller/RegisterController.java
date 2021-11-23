package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterSusscesDto;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final UserRegisterDtoConverter userRegisterDtoConverter;


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
}
