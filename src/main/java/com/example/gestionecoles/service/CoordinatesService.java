package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.Coordinates;
import com.example.gestionecoles.repository.CoordinatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoordinatesService {
    @Autowired
    private CoordinatesRepository repo;
    public List<Coordinates> findAll() { return repo.findAll(); }
    public Optional<Coordinates> findById(Integer id) { return repo.findById(id); }
    public Coordinates save(Coordinates c) { return repo.save(c); }
    public void delete(Integer id) { repo.deleteById(id); }
}
