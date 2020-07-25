package com.benhirt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nom", nullable = false)
    @Size(min=3, message = "Entrez au moins 3 caractères")
    private String nom;

    @Column(name = "prenom", nullable = false)
    @Size(min = 3, message = "Entrez au moins 3 caractères")
    private String prenom;

    @Email(message = "email invalid")
    @NotEmpty(message = "Ne laissez pas le champ vide")
    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Size(min = 6, message = "6 catacters au min")
    @NotEmpty(message = "Ne laissez pas le champ vide")
    @Column(name="password", nullable = false)
    private String password;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "salaire")
    private double salaire;

    @Column(name = "chiffreAffaire")
    private double chiffre;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="manager_id")
    private Employee manager;

    @OneToMany(mappedBy="manager")
    private Set<Employee> sousjacents = new HashSet<Employee>();

    @ManyToOne
    @JoinColumn(name="department_id")
    private Departement department;

    /*@OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="employee_id" )
    private List<Remuneration> remuneration;*/

    public Employee(long id)
    {
        this.id=id;
    }

    @ManyToOne
    private Departement departement;

    @Size(min=1,message = "selectionner au moins une tag")
    @ManyToMany
    @JoinTable(name="employees_soujacents", joinColumns={@JoinColumn(referencedColumnName="id")}
            , inverseJoinColumns={@JoinColumn(referencedColumnName="id")})
    private List<SousJacents> sousJacentsList;


}
