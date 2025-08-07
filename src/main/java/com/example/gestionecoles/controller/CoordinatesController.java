package com.example.gestionecoles.controller;

import com.example.gestionecoles.entity.Coordinates;
import com.example.gestionecoles.service.CoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coordinates")
public class CoordinatesController {

    @Autowired
    private CoordinatesService coordinatesService;

    @GetMapping
    public List<Coordinates> getAllCoordinates() {
        return coordinatesService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinates> getCoordinatesById(@PathVariable Integer id) {
        return coordinatesService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coordinates createCoordinates(@RequestBody Coordinates coordinates) {
        return coordinatesService.save(coordinates);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordinates> updateCoordinates(@PathVariable Integer id, @RequestBody Coordinates coordinates) {
        return coordinatesService.findById(id).map(existing -> {
            coordinates.setId(id);
            return ResponseEntity.ok(coordinatesService.save(coordinates));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteCoordinates(@PathVariable Integer id) {
        coordinatesService.delete(id);
    }
}