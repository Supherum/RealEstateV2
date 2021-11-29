package com.salesianos.triana.realestate.v2.vivienda.repository;

import com.salesianos.triana.realestate.v2.usuario.model.Usuario;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ViviendaRepository extends JpaRepository<Vivienda, UUID> , JpaSpecificationExecutor<Vivienda> {


    @Query("Select v from Vivienda v Where id=:id")
    Optional<Vivienda> finViviendaAll(@Param("id") UUID id);

    @Query("Select v from Vivienda v Where v.usuario=:u")
    List<Vivienda> findViviendasUsuario(@Param("u")Usuario u);

    @Query("""
            Select new  com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaInteresaDto(
               v.id, v.titulo, v.precio, v.descripcion,
               
                (SELECT new com.salesianos.triana.realestate.v2.interesa.model.Interesa
                (CASE WHEN (count(i.id))>0 THEN true ELSE false END),
                FROM Interesa i 
                    WHERE i.id.vivienda_id=v.id and i.id.usuario_id=:id)
            )
            from Vivienda v
            
            """)
    List<Vivienda> magiaViviendas(@Param("id")UUID id);
}
