package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterSusscesDto;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final AuthService authService;
    private final UserRegisterDtoConverter userRegisterDtoConverter;


    @PostMapping("/auth/register/user")
    public ResponseEntity<UserRegisterSusscesDto> nuevoUsuario (@RequestBody UserRegisterDto dto){

        Usuario u= authService.saveUser(dto);
        if(u!=null){
            authService.save(u);
            return ResponseEntity.status(HttpStatus.CREATED).body(userRegisterDtoConverter.UserToUserSussces(u));
        }
        else
            return ResponseEntity.badRequest().build();
    }
}
