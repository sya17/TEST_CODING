package com.example.test_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.test_spring.entity.RUserEntity;

public interface RUserRepository extends JpaRepository<RUserEntity, Integer> {

    List<RUserEntity> findUserAllByDepartementId(Integer id);
}
