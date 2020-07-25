package com.benhirt.converter;

import com.benhirt.entities.Departement;
import org.springframework.core.convert.converter.Converter;

public class DepartementConverter implements Converter<String, Departement> {
    @Override
    public Departement convert(String source) {
        if (source.isEmpty()) return null;
        else {
            return new Departement(Long.parseLong(source));
        }

    }
}
