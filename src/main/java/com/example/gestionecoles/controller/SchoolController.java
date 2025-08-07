package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.School;
import com.example.gestionecoles.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        return schoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public School createSchool(@RequestBody School school) {
        return schoolService.save(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Integer id, @RequestBody School school) {
        return schoolService.findById(id).map(existing -> {
            school.setId(id);
            return ResponseEntity.ok(schoolService.save(school));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteSchool(@PathVariable Integer id) {
        schoolService.deleteSchool(id);
    }
}