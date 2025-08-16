package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Integer> {
    public Level findByName(String name);
}
