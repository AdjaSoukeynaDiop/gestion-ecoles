package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.Review;
import com.example.gestionecoles.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repo;
    public List<Review> findAll() { return repo.findAll(); }
    public Optional<Review> findById(Integer id) { return repo.findById(id); }
    public Review save(Review r) { return repo.save(r); }
    public void delete(Integer id) { repo.deleteById(id); }
}
