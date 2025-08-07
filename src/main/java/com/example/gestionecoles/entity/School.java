package com.example.gestionecoles.entity;

import jakarta.persistence.*;
import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

// School.java
@Entity
@Table(name = "school")
public class School {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SchoolType type;

    private String address;
    private String region;
    private Integer students;
    private Float rating;
    private String phone;
    private String email;
    private Integer established;
    private String facilities;
    private String images;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    private Coordinates coordinates;

    @ManyToMany
    @JoinTable(
            name = "school_level",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private Set<Level> levels = new HashSet<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEstablished(Integer established) {
        this.established = established;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}