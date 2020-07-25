package com.benhirt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "dept")
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "dname", nullable = false)
    private String dname;

    @Column(name = "type", nullable = false)
    private String type;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="departement_id")
    private List<Employee> employees;
    @OneToMany(mappedBy = "departementList")
    private List<Departement> listDepartement;

    public Departement(long id)
    {
        this.id=id;
    }
}
