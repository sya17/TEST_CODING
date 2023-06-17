package com.example.test_spring.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter // Untuk Set Paramater
@Getter // Untuk Get Paramater
@Data
@Entity
@Table(name = "r_user")
public class RUserEntity {

    @Id
    @Column(name = "r_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rUserId;

    @Column(name = "username")
    private String username;

    @Column(name = "nama_depan")
    private String namaDepan;

    @Column(name = "nama_belakang")
    private String namaBelakang;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "departemen_id")
    private Integer departementId;
    // // @Column(name = "departemen_id")
    // @ManyToOne(targetEntity = RDepartementEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // @JoinColumn(name = "departement_id")
    // @JsonProperty("departement_id")
    // private RDepartementEntity departement;
}
