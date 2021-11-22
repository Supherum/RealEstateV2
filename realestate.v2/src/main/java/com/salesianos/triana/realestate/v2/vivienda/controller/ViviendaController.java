package com.salesianos.triana.realestate.v2.vivienda.controller;

import com.salesianos.triana.realestate.v2.inmobiliaria.service.InmobiliariaService;
import com.salesianos.triana.realestate.v2.interesa.service.InteresaService;
import com.salesianos.triana.realestate.v2.interesa.dto.InteresaDtoConverter;
import com.salesianos.triana.realestate.v2.usuario.service.UsuarioService;
import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaDetalleDtoConverter;
import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaListaDtoConverter;
import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendaPropietarioConverterDto;
import com.salesianos.triana.realestate.v2.vivienda.dto.ViviendasTopDtoConverter;
import com.salesianos.triana.realestate.v2.vivienda.service.ViviendaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/vivienda")
@Tag(name = "Controlador de los vivienda")

public class ViviendaController {

    private final ViviendaService viviendaService;
    private final UsuarioService propietarioService;
    private final InmobiliariaService inmobiliariaService;
    private final InteresaService interesaService;

    private final ViviendaPropietarioConverterDto viviendaPropietarioConverterDto;
    private final ViviendaDetalleDtoConverter viviendaDetalleDtoConverter;
    private final ViviendaListaDtoConverter viviendaListaDtoConverter;
    private final ViviendasTopDtoConverter viviendasTopDtoConverter;
    private final InteresaDtoConverter interesaDtoConverter;


}
