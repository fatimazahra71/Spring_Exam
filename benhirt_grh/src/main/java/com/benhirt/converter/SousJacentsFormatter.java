package com.benhirt.converter;

import com.benhirt.entities.SousJacents;
import lombok.SneakyThrows;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

public class SousJacentsFormatter extends CustomCollectionEditor {

    public SousJacentsFormatter(Class<? extends Collection> collectionType ) {
        super(collectionType);
    }

    @SneakyThrows
    @Override
    protected Object convertElement(Object element) {
        if(!element.equals(null)) {
            SousJacents sousJacents =  new SousJacents(Long.parseLong(String.valueOf(element)));
            return sousJacents;
        }else{
            return null;
        }
    }
}
