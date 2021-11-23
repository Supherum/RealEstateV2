package com.salesianos.triana.realestate.v2.shared.security.jwt;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Log
@Service
public class JwtManager {

    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.secret:elsecretoibericoeselquemasmegusta}")
    private String jwtSecret;

    @Value("${jwt.duration:43200}") // Está en segundos y equivale a 12 horas
    private int jwtLifeInSeconds;

    private JwtParser parser;


    // Encripta el secreto de Jwt en bytes
    @PostConstruct
    public void init() {
        parser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build();
    }

    // Busca al Usuario por su ID
    public UUID findUsuarioByUUID (String token) {
        return UUID.fromString(parser.parseClaimsJws(token).getBody().getSubject());
    }


    // Valida el token o lanza una excepción lo metemos en un try catch para coger las excepciones que lanza
    public boolean validateToken(String token) {
        try {
            // con parser.parseClaimsJws es un método que tiene implementado que valida el token
            parser.parseClaimsJws(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
            log.info("Peta en: " + ex.getMessage());
        }
        return false;
    }


    // Es el generador de token
    public String generateToken(Authentication authentication) {

        // Primero cogemos al usuario
        Usuario user = (Usuario) authentication.getPrincipal();

        // Añadimos el tiempo de expiración del token
        Date tokenExpirationDate = Date
                .from(LocalDateTime
                        .now()
                        .plusSeconds(jwtLifeInSeconds)
                        .atZone(ZoneId.systemDefault()).toInstant());

        // Creamos el token Jwt
        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setSubject(user.getId().toString())
                .setIssuedAt(tokenExpirationDate)
                .claim("nombre", user.getNombre())
                .claim("apellidos", user.getApellidos())
                .claim("nick", user.getNick())
                .claim("role", user.getRol().name())
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }
}
