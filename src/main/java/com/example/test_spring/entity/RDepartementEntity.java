package com.example.test_spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter // Untuk Set Paramater
@Getter // Untuk Get Paramater
@Data
@Entity
@Table(name = "r_departemen")
public class RDepartementEntity {

    @Id
    @Column(name = "r_departemen_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rDepartementId;
    
    @Column( name = "name_departemen" )
    private String nameDepartement;
}
