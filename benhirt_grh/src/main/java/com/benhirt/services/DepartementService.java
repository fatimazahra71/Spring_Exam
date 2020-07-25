package com.benhirt.services;

import com.benhirt.entities.Departement;
import com.benhirt.entities.Employee;
import com.benhirt.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface DepartementService {
    public Page<Departement> getAllDepartements(Optional<Integer> pageNo, Integer pageSize, String sortBy);
    public List<Departement> getAllDepartements();
    public Departement findById(long id) throws ResourceNotFoundException;
    public  void save(Departement departement);
    public void deleteById(long id);
    public List<Employee> getEmployeesOfDepartement(Long id);
    public boolean isDepartementEmployee(long idDepartement , long idEmployee);
}
