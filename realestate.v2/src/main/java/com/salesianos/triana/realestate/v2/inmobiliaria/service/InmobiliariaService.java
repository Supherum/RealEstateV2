package com.salesianos.triana.realestate.v2.inmobiliaria.service;

import com.salesianos.triana.realestate.v2.inmobiliaria.model.Inmobiliaria;
import com.salesianos.triana.realestate.v2.inmobiliaria.repository.InmobiliariaRepository;
import com.salesianos.triana.realestate.v2.shared.service.BaseService;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class InmobiliariaService extends BaseService<Inmobiliaria, UUID, InmobiliariaRepository> {

    private final ViviendaService viviendaService;

    @Override
    public void delete(Inmobiliaria inmobiliaria) {

        if (!inmobiliaria.getListaViviendas().isEmpty()) {
            inmobiliaria.getListaViviendas().stream().map(v -> {
                v.setInmobiliaria(null);
                return v;
            }).forEach(viviendaService::save);

        }

        super.delete(inmobiliaria);

    }
}
