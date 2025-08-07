package com.example.gestionecoles.controller;


import com.example.gestionecoles.entity.Level;
import com.example.gestionecoles.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @GetMapping
    public List<Level> getAllLevels() {
        return levelService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable Integer id) {
        return levelService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Level createLevel(@RequestBody Level level) {
        return levelService.save(level);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable Integer id, @RequestBody Level level) {
        return levelService.findById(id).map(existing -> {
            level.setId(id);
            return ResponseEntity.ok(levelService.save(level));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteLevel(@PathVariable Integer id) {
        levelService.delete(id);
    }
}