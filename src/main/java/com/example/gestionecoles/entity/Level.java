package com.example.gestionecoles.entity;

import jakarta.persistence.*;

// Level.java
@Entity
@Table(name = "level")
public class Level {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

