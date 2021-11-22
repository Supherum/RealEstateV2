package com.salesianos.triana.realestate.v2.vivienda.dto;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.stereotype.Component;

@Component
public class ViviendaPropietarioConverterDto {

    public ViviendaPropietarioDto viviendaToViviendaPropietarioDto (Vivienda v){
        return ViviendaPropietarioDto.builder()
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .latLng(v.getLatLng())
                .direccion(v.getDireccion())
                .codPostal(v.getCodPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tienePiscina(v.getTienePiscina())
                .tieneAscensor(v.getTieneAscensor())
                .tieneGaraje(v.getTieneGaraje())
                .numBanos(v.getNumBanos())
                .numHabitaciones(v.getNumHabitaciones())
                .precio(v.getPrecio())
                .metrosCuadrados(v.getMetrosCuadrados())
                .tipo(v.getTipo())
                .id(v.getId())
                .nombre(v.getUsuario().getNombre())
                .apellidos(v.getUsuario().getApellidos())
                .direccionPropietario(v.getUsuario().getDireccion())
                .email(v.getUsuario().getEmail())
                .telefono(v.getUsuario().getTelefono())
                .avatarPropietario(v.getUsuario().getAvatar())
                .inmobiliariaNombre(v.getInmobiliaria()!=null ? v.getInmobiliaria().getNombre() : "Sin asignar")
                .build();
    }

    public Vivienda getVivienda (ViviendaPropietarioDto dto){
        return Vivienda.builder()
                .titulo(dto.getTitulo())
                .direccion(dto.getDireccion())
                .descripcion(dto.getDescripcion())
                .metrosCuadrados(dto.getMetrosCuadrados())
                .avatar(dto.getAvatar())
                .latLng(dto.getLatLng())
                .direccion(dto.getDireccion())
                .codPostal(dto.getCodPostal())
                .poblacion(dto.getPoblacion())
                .provincia(dto.getProvincia())
                .tienePiscina(dto.getTienePiscina())
                .tieneAscensor(dto.getTieneAscensor())
                .tieneGaraje(dto.getTieneGaraje())
                .numHabitaciones(dto.getNumHabitaciones())
                .numBanos(dto.getNumBanos())
                .precio(dto.getPrecio())
                .tipo(dto.getTipo())
                .build();
    }

    public Usuario getPropietario(ViviendaPropietarioDto dto){
        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellidos(dto.getApellidos())
                .direccion(dto.getDireccionPropietario())
                .email(dto.getEmail())
                .telefono(dto.getTelefono())
                .avatar(dto.getAvatarPropietario())
                .build();
    }
}
