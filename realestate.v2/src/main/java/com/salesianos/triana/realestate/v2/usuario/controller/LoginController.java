package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.shared.security.jwt.JwtManager;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginResponseConverterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginResponseDto;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final UserLoginResponseConverterDto userLoginResponseConverterDto;

    @PostMapping("/auth/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginDto dto) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                dto.getNick(),
                                dto.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Devolver una respuesta adecuada
        // que incluya el token del usuario.
        String jwt = jwtManager.generateToken(authentication);


        Usuario user = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userLoginResponseConverterDto.UserAndJwtToResponse(user,jwt));

    }

    @GetMapping("/me")
    public ResponseEntity<?> quienSoyYo(@AuthenticationPrincipal Usuario user){
        return ResponseEntity.ok(userLoginResponseConverterDto.UserAndJwtToResponse(user,null));
    }



}
