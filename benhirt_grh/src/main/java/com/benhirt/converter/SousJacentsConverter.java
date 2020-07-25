package com.benhirt.converter;

import com.benhirt.entities.SousJacents;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class SousJacentsConverter implements Converter<List<String>, Object> {

    @Override
    public List<SousJacents> convert(List<String> source) {
        List<SousJacents> listSousJacents =new ArrayList<>();
        if(source.isEmpty()) {
            return null;
        }else{
            source.forEach(e-> listSousJacents.add(new SousJacents(Long.parseLong(e))));
            return listSousJacents;
        }
    }
}