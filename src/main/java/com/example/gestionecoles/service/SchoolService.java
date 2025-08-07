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
    private SchoolRepository schoolRepository;
    public List<School> findAll() { return schoolRepository.findAll(); }
    public Optional<School> findById(Integer id) { return schoolRepository.findById(id); }
    public School save(School s) { return schoolRepository.save(s); }
    public School updateSchool(Integer id, School school) {
        school.setId(id);
        return schoolRepository.save(school);
    }
    public void deleteSchool(Integer id) {
        schoolRepository.deleteById(id);
    }
}

