package com.benhirt.repositories;

import com.benhirt.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    Page<Employee> findAll(Pageable pageable); // revoie limiter les element par page


    @Query("select e from Employee e left join fetch e.sousJacentsList where e.id=:id")
    Employee findByIdWithSousJacents(@Param("id") long id);


    @Query("select e from Employee e where e.id=:EId and e.department.id=:dId")
    public Employee getEmployeeByDepartement(@Param("dId") long departement, @Param("EId") long employee);



}