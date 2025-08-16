package com.example.gestionecoles.controller;

import com.example.gestionecoles.dto.ReviewRequestDTO;
import com.example.gestionecoles.entity.Review;
import com.example.gestionecoles.entity.School;
import com.example.gestionecoles.entity.User;
import com.example.gestionecoles.exception.ResourceNotFoundException;
import com.example.gestionecoles.service.ReviewService;
import com.example.gestionecoles.service.SchoolService;
import com.example.gestionecoles.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    
    private final SchoolService schoolService;
    
    private final UserService userService;

    public ReviewController(UserService userService, SchoolService schoolService, ReviewService reviewService) {
        this.userService = userService;
        this.schoolService = schoolService;
        this.reviewService = reviewService;
    }

    @GetMapping("/school/{schoolId}")
    public Page<Review> getSchoolReviews(@PathVariable Integer schoolId, Pageable pageable) {
        School school = schoolService.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
        return reviewService.findBySchoolId(schoolId, pageable);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewRequestDTO request, 
                                             Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        School school = schoolService.findById(request.getSchoolId())
                .orElseThrow(() -> new ResourceNotFoundException("School", "id", request.getSchoolId()));

        Review review = new Review();
        review.setUser(user);
        review.setSchool(school);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setDate(new Timestamp(System.currentTimeMillis()));

        Review savedReview = reviewService.save(review);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Integer id) {
        Review review = reviewService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Integer id, 
                                             @Valid @RequestBody ReviewRequestDTO request,
                                             Authentication authentication) {
        Review review = reviewService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        
        String email = authentication.getName();
        if (!review.getUser().getEmail().equals(email)) {
            throw new RuntimeException("You can only update your own reviews");
        }

        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setDate(new Timestamp(System.currentTimeMillis()));

        Review updatedReview = reviewService.save(review);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id, Authentication authentication) {
        Review review = reviewService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review", "id", id));
        
        String email = authentication.getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        
        if (!review.getUser().getEmail().equals(email) && !user.getRole().name().equals("ADMIN")) {
            throw new RuntimeException("You can only delete your own reviews or be an admin");
        }

        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}