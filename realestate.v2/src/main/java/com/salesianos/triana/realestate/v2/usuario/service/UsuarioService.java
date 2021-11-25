package com.salesianos.triana.realestate.v2.usuario.service;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService extends BaseService<Usuario, UUID, UsuarioRepository> {

    public List<Usuario> findPropietarios (){
        return repository.findByRol(Rol.Propietario);
    }

    public List<Usuario> findGestores(){
        return repository.findByRol(Rol.Gestor);
    }

    public List<Usuario> findGestoresInmobiliaria(Inmobiliaria i){return repository.findByInmobiliaria(i);}
}
