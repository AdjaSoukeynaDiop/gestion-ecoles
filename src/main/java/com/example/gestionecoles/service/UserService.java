package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public List<User> findAll() { return repo.findAll(); }
    public Page<User> findAll(Pageable pageable) { return repo.findAll(pageable); }
    public Optional<User> findByEmail(String email) { return repo.findByEmail(email); }
    public Optional<User> findById(Integer id) { return repo.findById(id); }
    public User save(User user) { return repo.save(user); }
    public void delete(Integer id) { repo.deleteById(id); }
}