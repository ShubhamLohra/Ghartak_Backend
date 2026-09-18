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
    private String iconName;
    private String description;
    private String badgeText; // e.g. "02+02", "Labour/Mistry/Contractor/Engineer", "01 (Afrin)"
    private String categoryGroup; // Hardware, Home Care, Vehicle, Construction, Health
    private String bgGradient;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceItem> services = new ArrayList<>();

    public ServiceCategory() {}

    public ServiceCategory(String name, String code, String iconName, String description, String badgeText, String categoryGroup, String bgGradient) {
        this.name = name;
        this.code = code;
        this.iconName = iconName;
        this.description = description;
        this.badgeText = badgeText;
        this.categoryGroup = categoryGroup;
        this.bgGradient = bgGradient;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIconName() { return iconName; }
    public void setIconName(String iconName) { this.iconName = iconName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBadgeText() { return badgeText; }
    public void setBadgeText(String badgeText) { this.badgeText = badgeText; }

    public String getCategoryGroup() { return categoryGroup; }
    public void setCategoryGroup(String categoryGroup) { this.categoryGroup = categoryGroup; }

    public String getBgGradient() { return bgGradient; }
    public void setBgGradient(String bgGradient) { this.bgGradient = bgGradient; }

    public List<ServiceItem> getServices() { return services; }
    public void setServices(List<ServiceItem> services) { this.services = services; }
}
