package com.ghartak.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "service_leads")
public class ServiceLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leadCode;
    private String serviceCategory;
    private String customerName;
    private String customerPhone;
    private String location;
    private String distance; // e.g. "2.4 km away"
    private Double estimatedPayout; // e.g., 450.0 (matching ₹450 daily lead earnings note)
    private String timeSlot;
    private String requirementDetails;
    private String leadStatus; // AVAILABLE, ACCEPTED, EXPIRED

    @ManyToOne
    @JoinColumn(name = "assigned_worker_id")
    private User assignedWorker;

    private LocalDateTime createdAt = LocalDateTime.now();

    public ServiceLead() {}

    public ServiceLead(String leadCode, String serviceCategory, String customerName, String customerPhone, String location, String distance, Double estimatedPayout, String timeSlot, String requirementDetails, String leadStatus) {
        this.leadCode = leadCode;
        this.serviceCategory = serviceCategory;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.location = location;
        this.distance = distance;
        this.estimatedPayout = estimatedPayout;
        this.timeSlot = timeSlot;
        this.requirementDetails = requirementDetails;
        this.leadStatus = leadStatus;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLeadCode() { return leadCode; }
    public void setLeadCode(String leadCode) { this.leadCode = leadCode; }

    public String getServiceCategory() { return serviceCategory; }
    public void setServiceCategory(String serviceCategory) { this.serviceCategory = serviceCategory; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDistance() { return distance; }
    public void setDistance(String distance) { this.distance = distance; }

    public Double getEstimatedPayout() { return estimatedPayout; }
    public void setEstimatedPayout(Double estimatedPayout) { this.estimatedPayout = estimatedPayout; }

    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }

    public String getRequirementDetails() { return requirementDetails; }
    public void setRequirementDetails(String requirementDetails) { this.requirementDetails = requirementDetails; }

    public String getLeadStatus() { return leadStatus; }
    public void setLeadStatus(String leadStatus) { this.leadStatus = leadStatus; }

    public User getAssignedWorker() { return assignedWorker; }
    public void setAssignedWorker(User assignedWorker) { this.assignedWorker = assignedWorker; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
