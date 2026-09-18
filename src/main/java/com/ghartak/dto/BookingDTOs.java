package com.ghartak.dto;

import java.util.List;

public class BookingDTOs {

    public static class BookingItemRequest {
        private String title;
        private Double price;
        private Integer quantity;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public Double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }
        public Integer getQuantity() { return quantity; }
        public void setQuantity(Integer quantity) { this.quantity = quantity; }
    }

    public static class CreateBookingRequest {
        private Long customerId;
        private String serviceCategoryName;
        private String scheduledDate; // YYYY-MM-DD
        private String scheduledTimeSlot;
        private String address;
        private String city;
        private String pincode;
        private String contactPhone;
        private String instructions;
        private Double totalAmount;
        private Double taxesAndFee;
        private String paymentMethod;
        private List<BookingItemRequest> items;

        public Long getCustomerId() { return customerId; }
        public void setCustomerId(Long customerId) { this.customerId = customerId; }
        public String getServiceCategoryName() { return serviceCategoryName; }
        public void setServiceCategoryName(String serviceCategoryName) { this.serviceCategoryName = serviceCategoryName; }
        public String getScheduledDate() { return scheduledDate; }
        public void setScheduledDate(String scheduledDate) { this.scheduledDate = scheduledDate; }
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
        public String getPaymentMethod() { return paymentMethod; }
        public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
        public List<BookingItemRequest> getItems() { return items; }
        public void setItems(List<BookingItemRequest> items) { this.items = items; }
    }
}
