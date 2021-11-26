package com.salesianos.triana.realestate.v2.usuario.controller;

import com.salesianos.triana.realestate.v2.shared.security.jwt.JwtManager;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginResponseConverterDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserLoginResponseDto;
import com.salesianos.triana.realestate.v2.usuario.dto.auth.UserRegisterDto;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;
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

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtManager jwtManager;
    private final UserLoginResponseConverterDto userLoginResponseConverterDto;
    private final AuthService authService;

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


    @PostMapping("/auth/register/login")
    public ResponseEntity<UserLoginResponseDto> registerAndLogin(@RequestBody UserRegisterDto dto) {

        // REGISTRO

        Optional<Usuario> usuario =authService.findByNick(dto.getNick());

        if(usuario.isPresent() || !dto.getPassword().equals(dto.getPassword2()))
            return ResponseEntity.badRequest().build();

        Usuario u= authService.saveUsuario(dto, Rol.Propietario);
        if(u==null)
            return ResponseEntity.badRequest().build();

        // LOGIN

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                u.getNick(),
                                u.getPassword()
                        )
                );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtManager.generateToken(authentication);

        Usuario user = (Usuario) authentication.getPrincipal();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userLoginResponseConverterDto.UserAndJwtToResponse(user,jwt));

    }



}
