package com.benhirt.converter;

import com.benhirt.entities.Employee;
import org.springframework.core.convert.converter.Converter;

public class EmployeeConverter implements Converter<String, Employee>{
        @Override
        public Employee convert(String source) {
            if (source.isEmpty()) return null;
            else {
                return new Employee(Long.parseLong(source));
            }

        }

}
