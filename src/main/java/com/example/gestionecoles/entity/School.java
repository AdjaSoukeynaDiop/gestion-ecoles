package com.example.gestionecoles.entity;

import com.example.gestionecoles.enums.SchoolType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// School.java
@Entity
@Data
@Table(name = "school")
public class School {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    private SchoolType type;

    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Region is required")
    private String region;
    
    @Min(value = 1, message = "Number of students must be at least 1")
    private Integer students;
    
    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Rating must be between 0 and 5")
    private Float rating;
    
    private String phone;
    
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;
    private Integer established;
    private String facilities;
    private String images;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @OneToOne(mappedBy = "school", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Coordinates coordinates;

    @ManyToMany
    @JoinTable(
            name = "school_level",
            joinColumns = @JoinColumn(name = "school_id"),
            inverseJoinColumns = @JoinColumn(name = "level_id")
    )
    private Set<Level> levels = new HashSet<>();

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();


}