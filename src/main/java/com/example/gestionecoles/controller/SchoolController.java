package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.School;
import com.example.gestionecoles.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolRepository schoolRepository;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        return schoolRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolRepository.save(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Integer id, @RequestBody School school) {
        return schoolRepository.findById(id).map(existing -> {
            school.setId(id);
            return ResponseEntity.ok(schoolRepository.save(school));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Integer id) {
        schoolRepository.deleteById(id);
    }
}