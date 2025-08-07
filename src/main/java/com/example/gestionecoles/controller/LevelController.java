package com.example.gestionecoles.controller;


import com.example.gestionecoles.entity.Level;
import com.example.gestionecoles.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    @Autowired
    private LevelRepository levelRepository;

    @GetMapping
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable Integer id) {
        return levelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Level createLevel(@RequestBody Level level) {
        return levelRepository.save(level);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable Integer id, @RequestBody Level level) {
        return levelRepository.findById(id).map(existing -> {
            level.setId(id);
            return ResponseEntity.ok(levelRepository.save(level));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable Integer id) {
        levelRepository.deleteById(id);
    }
}