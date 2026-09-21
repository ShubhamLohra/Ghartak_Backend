package com.ghartak.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    public enum Status {
        BOOKED,
        PROVIDER_ASSIGNED,
        EN_ROUTE,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingCode; // e.g. "GT-849201"

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private User provider;

    private String serviceCategoryName;
    private LocalDateTime scheduledDate;
    private String scheduledTimeSlot; // e.g., "10:00 AM - 12:00 PM"

    private String address;
    private String city;
    private String pincode;
    private String contactPhone;
    private String instructions;

    private Double totalAmount;
    private Double taxesAndFee;
    private Double commissionAmount = 0.0;
    private Double providerPayout = 0.0;

    private String paymentMethod; // UPI, Cash on Delivery, Card, Net Banking
    private String paymentStatus; // PENDING, PAID, REFUNDED

    private String cancelStage; // BOOKED_UNASSIGNED, ASSIGNED_BEFORE_DISPATCH, EN_ROUTE, ON_SITE
    private String cancellationReason;
    private String cancelledBy; // CUSTOMER, PROVIDER, ADMIN

    @Enumerated(EnumType.STRING)
    private Status status = Status.BOOKED;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookingItem> items = new ArrayList<>();

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Booking() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBookingCode() { return bookingCode; }
    public void setBookingCode(String bookingCode) { this.bookingCode = bookingCode; }

    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }

    public User getProvider() { return provider; }
    public void setProvider(User provider) { this.provider = provider; }

    public String getServiceCategoryName() { return serviceCategoryName; }
    public void setServiceCategoryName(String serviceCategoryName) { this.serviceCategoryName = serviceCategoryName; }

    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }

    public String getScheduledTimeSlot() { return scheduledTimeSlot; }
    public void setScheduledTimeSlot(String scheduledTimeSlot) { this.scheduledTimeSlot = scheduledTimeSlot; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public Double getTaxesAndFee() { return taxesAndFee; }
    public void setTaxesAndFee(Double taxesAndFee) { this.taxesAndFee = taxesAndFee; }

    public Double getCommissionAmount() { return commissionAmount; }
    public void setCommissionAmount(Double commissionAmount) { this.commissionAmount = commissionAmount; }

    public Double getProviderPayout() { return providerPayout; }
    public void setProviderPayout(Double providerPayout) { this.providerPayout = providerPayout; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }

    public String getCancelStage() { return cancelStage; }
    public void setCancelStage(String cancelStage) { this.cancelStage = cancelStage; }

    public String getCancellationReason() { return cancellationReason; }
    public void setCancellationReason(String cancellationReason) { this.cancellationReason = cancellationReason; }

    public String getCancelledBy() { return cancelledBy; }
    public void setCancelledBy(String cancelledBy) { this.cancelledBy = cancelledBy; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public List<BookingItem> getItems() { return items; }
    public void setItems(List<BookingItem> items) { this.items = items; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
