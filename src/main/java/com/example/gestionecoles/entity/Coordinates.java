package com.example.gestionecoles.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

// Coordinates.java
@Data
@Entity
@Table(name = "coordinates")
public class Coordinates {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "school_id", nullable = false)
    @JsonBackReference
    private School school;


}