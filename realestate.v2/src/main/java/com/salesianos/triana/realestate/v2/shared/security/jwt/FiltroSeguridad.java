package com.salesianos.triana.realestate.v2.shared.security.jwt;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Log
@Component
@RequiredArgsConstructor
public class FiltroSeguridad extends OncePerRequestFilter {

    private final AuthService authService;
    private final JwtManager jwtManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 1. Obtener el token de la petición (request)
        String token = getJwtFromRequest(request);

        // 2. Validar token
        try {
            if (StringUtils.hasText(token) && jwtManager.validateToken(token)) {

                //Long userId = jwtProvider.getUserIdFromJwt(token);
                UUID userId = jwtManager.findUsuarioByUUID(token);

                Optional<Usuario> optionalUser = authService.findById(userId);

                if (optionalUser.isPresent()) {
                    Usuario user = optionalUser.get();
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    user,
                                    user.getRol(),
                                    user.getAuthorities()
                            );
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);


                }
            }

        } catch (Exception ex) {
            // Informar en el log
            log.info("No se ha podido establecer el contexto de seguridad (" + ex.getMessage() + ")");
        }

        filterChain.doFilter(request, response);
        // 2.1 Si es válido, autenticamos al usuario

        // 2.2 Si no es válido, lanzamos una excepcion



    }

    private String getJwtFromRequest(HttpServletRequest request) {
        // Authorization: Bearer eltoken.qiemas.megusta
        String bearerToken = request.getHeader(jwtManager.TOKEN_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtManager.TOKEN_PREFIX)) {
            return bearerToken.substring(jwtManager.TOKEN_PREFIX.length());
        }
        return null;
    }
}
