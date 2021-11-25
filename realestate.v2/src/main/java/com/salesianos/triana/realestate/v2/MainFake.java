package com.salesianos.triana.realestate.v2;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
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
    private final InmobiliariaService inmobiliariaService;


    @PostConstruct
    public void mainFake() {

        UserRegisterDto u = UserRegisterDto.builder()
                .nick("admin")
                .nombre("Lus Miguel")
                .apellidos("Lopez Magana")
                .email("luismiguel@triana.salesianos.edi")
                .password("admin")
                .password2("admin")
                .build();
        authService.saveUsuario(u, Rol.Administrador);

        Inmobiliaria i1=inmobiliariaService.save(Inmobiliaria.builder().nombre("Inmobiliara Rosa").email("test@gmail.com").telefono("+31 435 5356 63").build());
        Inmobiliaria i2=inmobiliariaService.save(Inmobiliaria.builder().nombre("Inmobiliara Velga").email("test@gmail.com").telefono("+31 435 5356 63").build());
        Inmobiliaria i3=inmobiliariaService.save(Inmobiliaria.builder().nombre("Inmobiliara Arturo").email("test@gmail.com").telefono("+31 435 5356 63").build());
        Inmobiliaria i4=inmobiliariaService.save(Inmobiliaria.builder().nombre("Inmobiliara SauPaulo").email("test@gmail.com").telefono("+31 435 5356 63").build());
        Inmobiliaria i5=inmobiliariaService.save(Inmobiliaria.builder().nombre("Inmobiliara Horrido").email("test@gmail.com").telefono("+31 435 5356 63").build());

        Usuario u1=authService.saveUsuario(UserRegisterDto.builder().nick("Juan").nombre("Juan").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Gestor);
        Usuario u2=authService.saveUsuario(UserRegisterDto.builder().nick("Fran").nombre("Fran").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Gestor);
        Usuario u3=authService.saveUsuario(UserRegisterDto.builder().nick("Paco").nombre("Paco").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Gestor);
        Usuario u4=authService.saveUsuario(UserRegisterDto.builder().nick("Jose").nombre("Jose").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Gestor);
        Usuario u5=authService.saveUsuario(UserRegisterDto.builder().nick("Laura").nombre("Laura").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Gestor);

        u1.addInmobiliariaToUser(i1);
        u2.addInmobiliariaToUser(i2);
        u3.addInmobiliariaToUser(i3);
        u4.addInmobiliariaToUser(i4);
        u5.addInmobiliariaToUser(i5);

        authService.save(u1);
        authService.save(u2);
        authService.save(u3);
        authService.save(u4);
        authService.save(u5);


        Usuario u6=authService.saveUsuario(UserRegisterDto.builder().nick("Marta").nombre("Marta").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u7=authService.saveUsuario(UserRegisterDto.builder().nick("Ivan").nombre("Ivan").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u8=authService.saveUsuario(UserRegisterDto.builder().nick("Javier").nombre("Javier").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u9=authService.saveUsuario(UserRegisterDto.builder().nick("Koko").nombre("Koko").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u10=authService.saveUsuario(UserRegisterDto.builder().nick("Ligre").nombre("Ligre").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u11=authService.saveUsuario(UserRegisterDto.builder().nick("Gato").nombre("Gato").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u12=authService.saveUsuario(UserRegisterDto.builder().nick("Lince").nombre("Lince").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u13=authService.saveUsuario(UserRegisterDto.builder().nick("Oscar").nombre("Oscar").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u14=authService.saveUsuario(UserRegisterDto.builder().nick("Gorka").nombre("Gorka").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);
        Usuario u15=authService.saveUsuario(UserRegisterDto.builder().nick("Diego").nombre("Diego").apellidos("Ramirez").email("test@gmail.com").password("1234").password2("1234").build(), Rol.Propietario);

        Vivienda v6=viviendaService.save(Vivienda.builder().tipo(Type.Alquiler).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(4).numBanos(2).tieneAscensor(true).tieneGaraje(false).tienePiscina(false).descripcion("Muy maja la casa").titulo("Casa Paco").build());
        Vivienda v7=viviendaService.save(Vivienda.builder().tipo(Type.Alquiler).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(4).numBanos(3).tieneAscensor(false).tieneGaraje(true).tienePiscina(true).descripcion("Muy maja la casa").titulo("Paraiso").build());
        Vivienda v8=viviendaService.save(Vivienda.builder().tipo(Type.Venta).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(7).numBanos(2).tieneAscensor(true).tieneGaraje(false).tienePiscina(false).descripcion("Muy maja la casa").titulo("Tejana").build());
        Vivienda v9=viviendaService.save(Vivienda.builder().tipo(Type.Obra_Nueva).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(1).numBanos(0).tieneAscensor(false).tieneGaraje(true).tienePiscina(false).descripcion("Muy maja la casa").titulo("Bella Vista").build());
        Vivienda v10=viviendaService.save(Vivienda.builder().tipo(Type.Venta).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(4).numBanos(2).tieneAscensor(true).tieneGaraje(true).tienePiscina(false).descripcion("Muy maja la casa").titulo("Al turrroooon!").build());
        Vivienda v11=viviendaService.save(Vivienda.builder().tipo(Type.Obra_Nueva).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(5).numBanos(2).tieneAscensor(false).tieneGaraje(false).tienePiscina(true).descripcion("Muy maja la casa").titulo("Casa de abuelitos").build());
        Vivienda v12=viviendaService.save(Vivienda.builder().tipo(Type.Alquiler).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(6).numBanos(2).tieneAscensor(false).tieneGaraje(true).tienePiscina(false).descripcion("Muy maja la casa").titulo("Picapierda house").build());
        Vivienda v13=viviendaService.save(Vivienda.builder().tipo(Type.Obra_Nueva).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(2).numBanos(1).tieneAscensor(false).tieneGaraje(false).tienePiscina(true).descripcion("Muy maja la casa").titulo("Volandas").build());
        Vivienda v14=viviendaService.save(Vivienda.builder().tipo(Type.Venta).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(8).numBanos(5).tieneAscensor(false).tieneGaraje(true).tienePiscina(false).descripcion("Muy maja la casa").titulo("Carmona").build());
        Vivienda v15=viviendaService.save(Vivienda.builder().tipo(Type.Obra_Nueva).direccion("C/Conde de bustillos").avatar("avatar").codPostal("41945").latLng("3,231 -1,235").metrosCuadrados(140.0).poblacion("Sevilla").provincia("Sevilla").precio(150000f).numHabitaciones(4).numBanos(2).tieneAscensor(true).tieneGaraje(false).tienePiscina(false).descripcion("Muy maja la casa").titulo("Casa de verano").build());

        v6.addUsuarioToVivienda(u6); v7.addUsuarioToVivienda(u7);  v8.addUsuarioToVivienda(u8);  v9.addUsuarioToVivienda(u9);  v10.addUsuarioToVivienda(u10); v11.addUsuarioToVivienda(u11); v12.addUsuarioToVivienda(u12); v13.addUsuarioToVivienda(u13); v14.addUsuarioToVivienda(u14); v15.addUsuarioToVivienda(u15);
        v6.addInmobiliariaToVivienda(i1); v7.addInmobiliariaToVivienda(i2); v8.addInmobiliariaToVivienda(i3); v9.addInmobiliariaToVivienda(i4); v10.addInmobiliariaToVivienda(i5);

        viviendaService.save(v6);
        viviendaService.save(v7);
        viviendaService.save(v8);
        viviendaService.save(v9);
        viviendaService.save(v10);
        viviendaService.save(v11);
        viviendaService.save(v12);
        viviendaService.save(v13);
        viviendaService.save(v14);
        viviendaService.save(v15);

    }


}
