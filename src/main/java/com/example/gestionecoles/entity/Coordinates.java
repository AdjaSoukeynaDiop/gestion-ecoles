package com.example.gestionecoles.entity;

import jakarta.persistence.*;

// Coordinates.java
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
    private School school;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}