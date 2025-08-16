package com.example.gestionecoles.entity;

import jakarta.persistence.*;
import lombok.Data;

// Level.java
@Entity
@Data
@Table(name = "level")
public class Level {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;


}

