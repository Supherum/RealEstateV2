package com.salesianos.triana.realestate.v2.vivienda.search;

import com.salesianos.triana.realestate.v2.vivienda.model.Vivienda;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ViviendaSpecificationBuilder {

    private final List<FiltroVivienda> params = new ArrayList<>();

    public ViviendaSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new FiltroVivienda(key, operation, value));
        return this;
    }

    public Specification<Vivienda> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(ViviendaSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);


        // La condición final es un AND de todas las condiciones
        for(int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

}
