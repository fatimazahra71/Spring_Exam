package com.benhirt.entities;

import javax.persistence.*;
import java.util.List;

public class Role {
    @Id
    private short id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name="employees_roles", joinColumns={@JoinColumn(referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    private List<Employee> employeeList;
    
}
