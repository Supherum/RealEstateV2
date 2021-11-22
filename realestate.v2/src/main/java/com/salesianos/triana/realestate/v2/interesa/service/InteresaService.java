package com.salesianos.triana.realestate.v2.interesa.service;


import com.salesianos.triana.realestate.v2.interesa.model.Interesa;
import com.salesianos.triana.realestate.v2.interesa.model.InteresaPK;
import com.salesianos.triana.realestate.v2.interesa.repository.InteresaRepository;
import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class InteresaService extends BaseService<Interesa, InteresaPK, InteresaRepository> {

    @Autowired
    InteresaRepository interesaRepository;
    public List<Interesa> allInteresaDeUnaVivienda(Long id){
        return interesaRepository.allInteresaDeUnaVivienda(id);
    }

}
