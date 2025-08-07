package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {}
