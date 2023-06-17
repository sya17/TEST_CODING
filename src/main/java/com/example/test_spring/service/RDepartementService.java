package com.example.test_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_spring.entity.RDepartementEntity;
import com.example.test_spring.repository.RDepartementRepository;

@Service
public class RDepartementService {
    
    @Autowired
    RDepartementRepository repository;

    public RDepartementEntity save(RDepartementEntity entity) {
        return repository.save(entity);
    }

    public Optional<RDepartementEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public List<RDepartementEntity> getAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
