package com.example.gestionecoles.service;

import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    public List<User> findAll() { return repo.findAll(); }
    public Optional<User> findById(Integer id) { return repo.findById(id); }
    public User save(User user) { return repo.save(user); }
    public void delete(Integer id) { repo.deleteById(id); }
}