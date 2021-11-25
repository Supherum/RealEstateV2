package com.salesianos.triana.realestate.v2.inmobiliaria.repository;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, UUID> {

}
