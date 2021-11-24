package com.salesianos.triana.realestate.v2.interesa.repository;

import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.interesa.model.InteresaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface InteresaRepository extends JpaRepository<Interesa, InteresaPK> {

    @Query("select i from Interesa i where vivienda_id=:iden")
    public List<Interesa> allInteresaDeUnaVivienda (@Param("iden")UUID iden);

}
