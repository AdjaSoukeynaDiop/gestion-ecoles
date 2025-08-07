package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.School;
import com.example.gestionecoles.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository repo;
    public List<School> findAll() { return repo.findAll(); }
    public Optional<School> findById(Integer id) { return repo.findById(id); }
    public School save(School s) { return repo.save(s); }
    public void delete(Integer id) { repo.deleteById(id); }
}

