package com.ghartak.controller;

import com.ghartak.model.ServiceItem;
import com.ghartak.repository.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    @GetMapping
    public ResponseEntity<List<ServiceItem>> getAllServices() {
        return ResponseEntity.ok(serviceItemRepository.findAll());
    }

    @GetMapping("/popular")
    public ResponseEntity<List<ServiceItem>> getPopularServices() {
        return ResponseEntity.ok(serviceItemRepository.findByIsPopularTrue());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ServiceItem>> getServicesByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(serviceItemRepository.findByCategoryId(categoryId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceItem>> searchServices(@RequestParam("q") String query) {
        return ResponseEntity.ok(serviceItemRepository.searchServices(query));
    }
}
