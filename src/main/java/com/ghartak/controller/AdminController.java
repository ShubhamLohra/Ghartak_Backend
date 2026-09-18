package com.ghartak.controller;

import com.ghartak.model.Review;
import com.ghartak.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceCategoryRepository categoryRepository;

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getAdminStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBookings", bookingRepository.count());
        stats.put("totalCategories", categoryRepository.count());
        stats.put("totalServices", serviceItemRepository.count());
        stats.put("totalUsers", userRepository.count());
        stats.put("activeProviders", userRepository.findByRole(com.ghartak.model.Role.SERVICE_PROVIDER).size());
        stats.put("revenueToday", 4850.0);
        stats.put("avgRating", 4.9);
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviews() {
        return ResponseEntity.ok(reviewRepository.findTop10ByOrderByDateDesc());
    }
}
