package com.salesianos.triana.realestate.v2;

import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;
import com.salesianos.triana.realestate.v2.vivienda.model.Type;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@RequiredArgsConstructor
@Component
public class MainFake {

    private final AuthService authService;
    private final ViviendaService viviendaService;


    @PostConstruct
    public void mainFake(){

        UserRegisterDto u= UserRegisterDto.builder()
                .password("XD")
                .nick("Pepe")
                .nombre("Juan")
                .apellidos("Ramirez")
                .email("pepe@juan")
                .password2("XD")
                .build();

        authService.saveUsuario(u, Rol.Propietario);

        viviendaService.save(
        Vivienda.builder()
                .direccion("calle")
                .tieneAscensor(true)
                .tipo(Type.Obra_Nueva)
                .build());
    }


}
