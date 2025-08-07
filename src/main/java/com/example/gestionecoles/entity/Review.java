package com.example.gestionecoles.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    private String userName;

    @Column(nullable = false)
    private Integer rating;

    private String comment;

    private Timestamp date;

    // --- Getters ---

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public School getSchool() {
        return school;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getDate() {
        return date;
    }

    // --- Setters ---

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
