package com.benhirt.services;

import com.benhirt.entities.Employee;
import com.benhirt.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeService {

    public Page<Employee> getAllEmployees(Optional<Integer> pageNo, Integer pageSize, String sortBy);

    Employee findById(long id) throws ResourceNotFoundException;

    void save(Employee employee);

    void deleteById(long id);

    Employee findByIdWithSousJacents(@Param("id") long id);
}
