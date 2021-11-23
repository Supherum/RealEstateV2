package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/propietario/")
    public ResponseEntity<List<Usuario>> findPropietarios (){

        return ResponseEntity.ok(usuarioService.findPropietarios());
    }

}
