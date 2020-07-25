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

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name="sousjacents")
public class SousJacents {
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

    @Column(name = "grad", nullable = false)
    private String grad;

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


    @ManyToMany(mappedBy="sousJacentsList")
    List<Employee> employeeList;


    @Transient
    private Boolean used;

    public SousJacents(long id) {
        this.id=id;
    }
}
