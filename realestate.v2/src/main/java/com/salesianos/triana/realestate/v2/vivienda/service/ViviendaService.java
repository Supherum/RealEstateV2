package com.salesianos.triana.realestate.v2.vivienda.service;


import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import com.salesianos.triana.realestate.v2.vivienda.repository.ViviendaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViviendaService extends BaseService<Vivienda, UUID, ViviendaRepository> {


}
