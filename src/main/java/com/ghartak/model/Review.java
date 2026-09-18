package com.ghartak.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String userCity;
    private Double rating;
    private String serviceCategory;
    private String comment;
    private Boolean verifiedBooking = true;
    private LocalDateTime date = LocalDateTime.now();

    public Review() {}

    public Review(String userName, String userCity, Double rating, String serviceCategory, String comment) {
        this.userName = userName;
        this.userCity = userCity;
        this.rating = rating;
        this.serviceCategory = serviceCategory;
        this.comment = comment;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserCity() { return userCity; }
    public void setUserCity(String userCity) { this.userCity = userCity; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getServiceCategory() { return serviceCategory; }
    public void setServiceCategory(String serviceCategory) { this.serviceCategory = serviceCategory; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Boolean getVerifiedBooking() { return verifiedBooking; }
    public void setVerifiedBooking(Boolean verifiedBooking) { this.verifiedBooking = verifiedBooking; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
