package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.School;
import com.example.gestionecoles.enums.SchoolType;
import com.example.gestionecoles.repository.LevelRepository;
import com.example.gestionecoles.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    
    @Autowired
    private LevelRepository levelRepository;
    
    public List<School> findAll() {
        return schoolRepository.findAll();
    }
    
    public Page<School> findAll(Pageable pageable) { return schoolRepository.findAll(pageable); }
    public Optional<School> findById(Integer id) { return schoolRepository.findById(id); }
    public School save(School s) { return schoolRepository.save(s); }
    public School updateSchool(Integer id, School school) {
        school.setId(id);
        return schoolRepository.save(school);
    }
    public void deleteSchool(Integer id) {
        schoolRepository.deleteById(id);
    }
    
    public Page<School> searchSchools(String region, String type, String levelName, Pageable pageable) {
        Specification<School> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (region != null && !region.trim().isEmpty()) {
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("region")), 
                    "%" + region.toLowerCase() + "%"));
            }
            
            if (type != null && !type.trim().isEmpty()) {
                try {
                    SchoolType schoolType = SchoolType.valueOf(type.toUpperCase());
                    predicates.add(criteriaBuilder.equal(root.get("type"), schoolType));
                } catch (IllegalArgumentException ignored) {
                    // Invalid type, ignore this filter
                }
            }
            
            if (levelName != null && !levelName.trim().isEmpty()) {
                Join<Object, Object> levelsJoin = root.join("levels");
                predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(levelsJoin.get("name")), 
                    "%" + levelName.toLowerCase() + "%"));
            }
            
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        
        return schoolRepository.findAll(spec, pageable);
    }

}

