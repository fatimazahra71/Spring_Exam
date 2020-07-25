package com.benhirt.services;

import com.benhirt.entities.Employee;
import com.benhirt.exceptions.ResourceNotFoundException;
import com.benhirt.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Page<Employee> getAllEmployees(Optional<Integer> pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(0, pageSize, Sort.by(sortBy));
        if(pageNo.isPresent()){
            paging = PageRequest.of(pageNo.get(), pageSize, Sort.by(sortBy));
        }
        return employeeRepository.findAll(paging);
    }

    @Override
    @Transactional
    public Employee findById(long id) throws ResourceNotFoundException {
        return employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException(id)
        );
    }

    @Override
    @Transactional
    public void save(Employee employee) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        employee.setCreated(timestamp);
        employeeRepository.save(employee);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Employee findByIdWithSousJacents(long id) {
        return employeeRepository.findByIdWithSousJacents(id);
    }


}
