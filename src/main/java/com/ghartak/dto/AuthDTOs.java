package com.ghartak.dto;

import com.ghartak.model.Role;

public class AuthDTOs {

    public static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String email;
        private String password;
        private String fullName;
        private String phone;
        private String address;
        private String city;
        private String pincode;
        private Role role = Role.CUSTOMER;
        private String profession;

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
    }

    public static class AuthResponse {
        private String token;
        private Long userId;
        private String fullName;
        private String email;
        private String phone;
        private String role;
        private String profession;
        private String city;
        private String address;

        public AuthResponse(String token, Long userId, String fullName, String email, String phone, String role, String profession, String city, String address) {
            this.token = token;
            this.userId = userId;
            this.fullName = fullName;
            this.email = email;
            this.phone = phone;
            this.role = role;
            this.profession = profession;
            this.city = city;
            this.address = address;
        }

        public String getToken() { return token; }
        public Long getUserId() { return userId; }
        public String getFullName() { return fullName; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getRole() { return role; }
        public String getProfession() { return profession; }
        public String getCity() { return city; }
        public String getAddress() { return address; }
    }
}
