package com.example.gestionecoles.controller;

import com.example.gestionecoles.dto.SchoolRequestDTO;
import com.example.gestionecoles.entity.*;
import com.example.gestionecoles.enums.SchoolType;
import com.example.gestionecoles.exception.ResourceNotFoundException;
import com.example.gestionecoles.repository.LevelRepository;
import com.example.gestionecoles.service.ReviewService;
import com.example.gestionecoles.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    private final SchoolService schoolService;
    private final LevelRepository levelRepository;
    private final ReviewService reviewService;

    public SchoolController(SchoolService schoolService, LevelRepository levelRepository, 

                           ReviewService reviewService) {
        this.schoolService = schoolService;
        this.levelRepository = levelRepository;
        this.reviewService = reviewService;
    }


    @PostMapping
    public ResponseEntity<School> createSchool(@Valid @RequestBody SchoolRequestDTO request) {
        School school = new School();
        school.setName(request.getName());
        school.setType(SchoolType.valueOf(request.getType().toUpperCase()));
        school.setAddress(request.getAddress());
        school.setRegion(request.getRegion());
        school.setStudents(request.getStudents());
        school.setRating(request.getRating());
        school.setPhone(request.getPhone());
        school.setEmail(request.getEmail());
        school.setEstablished(request.getEstablished());
        school.setFacilities(request.getFacilities());
        school.setImages(request.getImages());

        Set<Level> levels = Arrays.stream(request.getLevels().split(","))
                .map(String::trim)
                .map(levelRepository::findByName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        school.setLevels(levels);

        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(request.getLat());
        coordinates.setLongitude(request.getLng());
        coordinates.setSchool(school);
        school.setCoordinates(coordinates);

        return ResponseEntity.ok(schoolService.save(school));
    }

    @GetMapping
    public List<School> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/search")
    public Page<School> searchSchools(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String level,
            Pageable pageable) {
        return schoolService.searchSchools(region, type, level, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        School school = schoolService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
        return ResponseEntity.ok(school);
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> updateSchool(@PathVariable Integer id, @Valid @RequestBody SchoolRequestDTO request) {
        return schoolService.findById(id).map(existing -> {
            existing.setName(request.getName());
            existing.setType(SchoolType.valueOf(request.getType().toUpperCase()));
            existing.setAddress(request.getAddress());
            existing.setRegion(request.getRegion());
            existing.setStudents(request.getStudents());
            existing.setRating(request.getRating());
            existing.setPhone(request.getPhone());
            existing.setEmail(request.getEmail());
            existing.setEstablished(request.getEstablished());
            existing.setFacilities(request.getFacilities());
            existing.setImages(request.getImages());

            Set<Level> levels = Arrays.stream(request.getLevels().split(","))
                    .map(String::trim)
                    .map(levelRepository::findByName)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet());
            existing.setLevels(levels);

            if (existing.getCoordinates() == null) {
                Coordinates coordinates = new Coordinates();
                coordinates.setSchool(existing);
                existing.setCoordinates(coordinates);
            }
            existing.getCoordinates().setLatitude(request.getLat());
            existing.getCoordinates().setLongitude(request.getLng());

            return ResponseEntity.ok(schoolService.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Integer id) {
        schoolService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
        schoolService.deleteSchool(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reviews")
    public Page<Review> getSchoolReviews(@PathVariable Integer id, Pageable pageable) {
        schoolService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", id));
        return reviewService.findBySchoolId(id, pageable);
    }
}
