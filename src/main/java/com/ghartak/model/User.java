package com.ghartak.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phone;

    private String address;
    private String city;
    private String pincode;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    private String profession; // For Service Providers (Electrician, Carpenter, etc.)
    private Double rating = 4.8;
    private Integer completedJobs = 0;
    private Double totalEarnings = 0.0;
    private Integer dailyLeadsRemaining = 3; // 3 leads per worker per day

    private LocalDateTime createdAt = LocalDateTime.now();

    public User() {}

    public User(String email, String password, String fullName, String phone, String address, String city, String pincode, Role role) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.pincode = pincode;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getCompletedJobs() { return completedJobs; }
    public void setCompletedJobs(Integer completedJobs) { this.completedJobs = completedJobs; }

    public Double getTotalEarnings() { return totalEarnings; }
    public void setTotalEarnings(Double totalEarnings) { this.totalEarnings = totalEarnings; }

    public Integer getDailyLeadsRemaining() { return dailyLeadsRemaining; }
    public void setDailyLeadsRemaining(Integer dailyLeadsRemaining) { this.dailyLeadsRemaining = dailyLeadsRemaining; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
