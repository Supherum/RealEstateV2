package com.salesianos.triana.realestate.v2.vivienda.repository;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> , JpaSpecificationExecutor<Vivienda> {


    @Query("Select v from Vivienda v Where id=:id")
    Optional<Vivienda> finViviendaAll(@Param("id") UUID id);
}
