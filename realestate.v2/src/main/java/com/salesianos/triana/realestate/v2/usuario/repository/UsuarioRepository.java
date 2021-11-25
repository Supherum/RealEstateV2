package com.salesianos.triana.realestate.v2.usuario.repository;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByNick(String nick);

    List <Usuario> findByRol (Rol rol);

    public List<Usuario> findByInmobiliaria(Inmobiliaria inmobiliaria);

}
