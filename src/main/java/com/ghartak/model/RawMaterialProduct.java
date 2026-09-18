package com.ghartak.model;

import jakarta.persistence.*;

@Entity
@Table(name = "raw_materials")
public class RawMaterialProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String category; // Cement, Steel TMT, Sand, Bricks, Plumbing Fittings, Electrical Wires
    private String supplierName;
    private Double price;
    private String unit; // per bag (50kg), per ton, per brass, per 1000 pcs
    private Double rating = 4.9;
    private Boolean isBulkAvailable = true;
    private Integer minimumOrder = 1;
    private String description;
    private String imageUrl;

    public RawMaterialProduct() {}

    public RawMaterialProduct(String name, String category, String supplierName, Double price, String unit, Double rating, Boolean isBulkAvailable, Integer minimumOrder, String description, String imageUrl) {
        this.name = name;
        this.category = category;
        this.supplierName = supplierName;
        this.price = price;
        this.unit = unit;
        this.rating = rating;
        this.isBulkAvailable = isBulkAvailable;
        this.minimumOrder = minimumOrder;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Boolean getIsBulkAvailable() { return isBulkAvailable; }
    public void setIsBulkAvailable(Boolean isBulkAvailable) { this.isBulkAvailable = isBulkAvailable; }

    public Integer getMinimumOrder() { return minimumOrder; }
    public void setMinimumOrder(Integer minimumOrder) { this.minimumOrder = minimumOrder; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
