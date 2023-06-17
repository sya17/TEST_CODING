package com.example.test_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test_spring.entity.RDepartementEntity;

public interface RDepartementRepository extends JpaRepository<RDepartementEntity, Integer> {

}