package com.salesianos.triana.realestate.v2.shared.security;

import com.salesianos.triana.realestate.v2.shared.security.jwt.Autenticacion;
import com.salesianos.triana.realestate.v2.shared.security.jwt.Autorizacion;
import com.salesianos.triana.realestate.v2.shared.security.jwt.FiltroSeguridad;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final Autenticacion autenticacion;
    private final Autorizacion autorizacion;

    private final PasswordEncoder codificador;
    private final UserDetailsService userDetailsService;

    private final FiltroSeguridad filtroSeguridad;



    // Nos traemos un objeto de tipo AuthenticationManager
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(codificador);
    }



    // La configuración de todas las rutas
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(autorizacion)
                .accessDeniedHandler(autenticacion)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register/propietario").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/register/gestor").hasRole("Administrador")
                .antMatchers(HttpMethod.POST, "/auth/register/administrador").hasRole("Administrador")
                .antMatchers(HttpMethod.GET, "/vivienda/").authenticated()
                .antMatchers(HttpMethod.POST, "/vivienda/").permitAll()
                .antMatchers(HttpMethod.GET, "/propietario/").authenticated()
                .anyRequest().permitAll();

        http.addFilterBefore(filtroSeguridad, UsernamePasswordAuthenticationFilter.class);

        // Para dar acceso a h2
        http.headers().frameOptions().disable();


    }
}
