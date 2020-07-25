package com.benhirt.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "remun")
public class Remuneration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    private String type;
    private double montant;
    private String date_remun;
    @ManyToOne
    private Employee employee;
}
