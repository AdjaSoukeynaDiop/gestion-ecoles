package com.example.gestionecoles.repository;


import com.example.gestionecoles.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {}