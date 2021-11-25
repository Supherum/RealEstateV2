package com.salesianos.triana.realestate.v2.vivienda.service;


import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.repository.ViviendaRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ViviendaService extends BaseService<Vivienda, UUID, ViviendaRepository> {

    public List<Vivienda> viviendaConSpecification (Specification<Vivienda> spec){return  repository.findAll(spec);}

    public Optional<Vivienda> findViviendaAll (UUID id){return repository.finViviendaAll(id);}
}
