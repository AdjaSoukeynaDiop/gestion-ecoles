package com.example.gestionecoles.repository;

import com.example.gestionecoles.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findBySchoolIdOrderByDateDesc(Integer schoolId, Pageable pageable);
}