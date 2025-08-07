package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.Coordinates;
import com.example.gestionecoles.repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesRepository coordinatesRepository;

    @GetMapping
    public List<Coordinates> getAllCoordinates() {
        return coordinatesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinates> getCoordinatesById(@PathVariable Integer id) {
        return coordinatesRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coordinates createCoordinates(@RequestBody Coordinates coordinates) {
        return coordinatesRepository.save(coordinates);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordinates> updateCoordinates(@PathVariable Integer id, @RequestBody Coordinates coordinates) {
        return coordinatesRepository.findById(id).map(existing -> {
            coordinates.setId(id);
            return ResponseEntity.ok(coordinatesRepository.save(coordinates));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteCoordinates(@PathVariable Integer id) {
        coordinatesRepository.deleteById(id);
    }
}