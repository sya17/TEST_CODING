package com.example.test_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test_spring.entity.RUserEntity;
import com.example.test_spring.repository.RUserRepository;

@Service
public class RUserService {

    @Autowired
    RUserRepository repository;

    public RUserEntity save(RUserEntity entity) {
        return repository.save(entity);
    }

    public Optional<RUserEntity> findById(Integer id) {
        return repository.findById(id);
    }

    public List<RUserEntity> findAll() {
        return repository.findAll();
    }

    public List<RUserEntity> findUserAllByDepartementId(Integer id) {
        return repository.findUserAllByDepartementId(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
