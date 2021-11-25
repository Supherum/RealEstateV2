package com.salesianos.triana.realestate.v2.usuario.repository;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.usuario.model.Rol;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findFirstByNick(String nick);

    List <Usuario> findByRol (Rol rol);

    List<Usuario> findByInmobiliaria(Inmobiliaria inmobiliaria);


    @EntityGraph(value = "grafo-interesado", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Usuario> findFirstById(UUID id);
}
