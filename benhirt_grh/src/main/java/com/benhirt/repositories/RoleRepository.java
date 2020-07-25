package com.benhirt.repositories;

import com.benhirt.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleRepository extends JpaRepository<Role,Short> {
    @Query("select d.listDepartement from Departement d where d.dname=:name")
    public List<Role> getRolesByName (@Param("name") String name);
}
