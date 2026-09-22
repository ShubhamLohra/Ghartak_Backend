package com.ghartak.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service_categories")
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String code;
    private String description;

    private Double baseCharge = 149.0;
    private Double commissionRate = 15.0; // 15% or ₹150
    private String commissionType = "PERCENTAGE"; // "PERCENTAGE" or "FIXED"

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceItem> services = new ArrayList<>();

    public ServiceCategory() {}

    public ServiceCategory(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public ServiceCategory(String name, String code, String description, Double baseCharge, Double commissionRate, String commissionType) {
        this(name, code, description);
        this.baseCharge = baseCharge;
        this.commissionRate = commissionRate;
        this.commissionType = commissionType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getBaseCharge() { return baseCharge; }
    public void setBaseCharge(Double baseCharge) { this.baseCharge = baseCharge; }

    public Double getCommissionRate() { return commissionRate; }
    public void setCommissionRate(Double commissionRate) { this.commissionRate = commissionRate; }

    public String getCommissionType() { return commissionType; }
    public void setCommissionType(String commissionType) { this.commissionType = commissionType; }

    public List<ServiceItem> getServices() { return services; }
    public void setServices(List<ServiceItem> services) { this.services = services; }
}
