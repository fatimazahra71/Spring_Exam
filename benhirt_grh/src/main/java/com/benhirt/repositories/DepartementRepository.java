package com.benhirt.repositories;

import com.benhirt.entities.Departement;
import com.benhirt.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Page<Departement> findAll(Pageable pageable);
    @Query("select e from  Employee e where e.department.id=:id")
    public List<Employee> getEmployeesById(@Param("id") long id);

    @Query("select d from Departement d where d.id =:id")
    public Departement getDepartementById(@Param("id") Long id);

}
