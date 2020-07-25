package com.benhirt.services;

import com.benhirt.entities.Departement;
import com.benhirt.entities.Employee;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.repositories.DepartementRepository;
import com.benhirt.repositories.EmployeeRepository;
import com.benhirt.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Page<Departement> getAllDepartements(Optional<Integer> pageNo, Integer pageSize, String sortBy) {
        if(pageNo.isPresent()){
            Pageable paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
            return departementRepository.findAll(paging);
        }else{
            Pageable paging = PageRequest.of(0, pageSize, Sort.by(sortBy));
            return departementRepository.findAll(paging);
        }
    }
    @Override
    @Transactional
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    @Transactional
    public Departement findById(long id) throws ResourceNotFoundException {
        return departementRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public void save(Departement departement){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //departement.setCreated(timestamp);
        departementRepository.save(departement);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        departementRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployeesOfDepartement(Long id) {
        return departementRepository.getEmployeesById(id);
    }

    @Override
    public boolean isDepartementEmployee(long idDepartement, long idEmployee) {
        return employeeRepository.getEmployeeByDepartement(idDepartement, idEmployee) !=null;
    }

}
