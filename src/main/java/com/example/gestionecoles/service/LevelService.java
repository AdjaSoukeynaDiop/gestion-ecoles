package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.Level;
import com.example.gestionecoles.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService {
    @Autowired
    private LevelRepository repo;
    public List<Level> findAll() { return repo.findAll(); }
    public Optional<Level> findById(Integer id) { return repo.findById(id); }
    public Level save(Level l) { return repo.save(l); }
    public void delete(Integer id) { repo.deleteById(id); }
}
