package com.example.gestionecoles.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class SchoolRequestDTO {
    @NotBlank(message = "School name is required")
    private String name;
    
    @NotBlank(message = "School type is required")
    @Pattern(regexp = "PUBLIC|PRIVATE", message = "Type must be PUBLIC or PRIVATE")
    private String type;
    
    @NotBlank(message = "Levels are required")
    private String levels;
    
    @NotBlank(message = "Address is required")
    private String address;
    
    @NotBlank(message = "Region is required")
    private String region;
    
    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double lat;
    
    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double lng;
    
    @Min(value = 1, message = "Number of students must be at least 1")
    private Integer students;
    
    private String facilities;
    
    @DecimalMin(value = "0.0", message = "Rating must be between 0 and 5")
    @DecimalMax(value = "5.0", message = "Rating must be between 0 and 5")
    private Float rating;
    
    @Pattern(regexp = "^\\+?[0-9\\s-()]+$", message = "Invalid phone number format")
    private String phone;
    
    @Email(message = "Invalid email format")
    private String email;
    
    @Min(value = 1800, message = "Establishment year must be after 1800")
    @Max(value = 2030, message = "Establishment year cannot be in the future")
    private Integer established;
    
    private String images;
}