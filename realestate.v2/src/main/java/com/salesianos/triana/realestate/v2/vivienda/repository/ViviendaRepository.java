package com.salesianos.triana.realestate.v2.vivienda.repository;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> {

}
