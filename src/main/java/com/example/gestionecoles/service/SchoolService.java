package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.*;
import com.example.gestionecoles.repository.CoordinatesRepository;
import com.example.gestionecoles.repository.LevelRepository;
import com.example.gestionecoles.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    private LevelRepository levelRepository;
    private CoordinatesRepository coordinatesRepository;
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

    public SchoolService(SchoolRepository schoolRepository,
                         LevelRepository levelRepository,
                         CoordinatesRepository coordinatesRepository) {
        this.schoolRepository = schoolRepository;
        this.levelRepository = levelRepository;
        this.coordinatesRepository = coordinatesRepository;
    }

    public School createSchool(SchoolRequestDto dto) {
        School school = new School();
        school.setName(dto.name);
        school.setType(SchoolType.valueOf(dto.type));
        school.setAddress(dto.address);
        school.setRegion(dto.region);
        school.setStudents(dto.students);
        school.setRating((float) dto.rating);
        school.setPhone(dto.phone);
        school.setEmail(dto.email);
        school.setEstablished(dto.established);
        school.setFacilities(dto.facilities);
        school.setImages(dto.images);
        school.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));

        // Création des coordonnées
        Coordinates coordinates = new Coordinates();
        coordinates.setLatitude(dto.latitude);
        coordinates.setLongitude(dto.longitude);
        coordinates.setSchool(school);
        coordinatesRepository.save(coordinates);
        school.setCoordinates(coordinates);

        return schoolRepository.save(school);
    }


}

