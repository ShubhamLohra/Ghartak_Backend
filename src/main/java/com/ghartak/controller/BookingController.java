package com.ghartak.controller;

import com.ghartak.dto.BookingDTOs.*;
import com.ghartak.model.Booking;
import com.ghartak.model.BookingItem;
import com.ghartak.model.User;
import com.ghartak.repository.BookingRepository;
import com.ghartak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingRepository.findAllByOrderByCreatedAtDesc());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> getBookingsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingRepository.findByCustomerIdOrderByCreatedAtDesc(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingRequest request) {
        Booking booking = new Booking();
        booking.setBookingCode("GT-" + (100000 + new Random().nextInt(900000)));

        if (request.getCustomerId() != null) {
            Optional<User> userOptional = userRepository.findById(request.getCustomerId());
            userOptional.ifPresent(booking::setCustomer);
        }

        // Auto-assign sample verified provider
        List<User> providers = userRepository.findByRole(com.ghartak.model.Role.SERVICE_PROVIDER);
        if (!providers.isEmpty()) {
            booking.setProvider(providers.get(new Random().nextInt(providers.size())));
        }

        booking.setServiceCategoryName(request.getServiceCategoryName());
        booking.setScheduledDate(LocalDateTime.now().plusDays(1));
        booking.setScheduledTimeSlot(request.getScheduledTimeSlot() != null ? request.getScheduledTimeSlot() : "10:00 AM - 12:00 PM");
        booking.setAddress(request.getAddress());
        booking.setCity(request.getCity() != null ? request.getCity() : "Delhi NCR");
        booking.setPincode(request.getPincode());
        booking.setContactPhone(request.getContactPhone());
        booking.setInstructions(request.getInstructions());
        booking.setTotalAmount(request.getTotalAmount());
        booking.setTaxesAndFee(request.getTaxesAndFee() != null ? request.getTaxesAndFee() : 49.0);
        booking.setPaymentMethod(request.getPaymentMethod() != null ? request.getPaymentMethod() : "Cash on Delivery");
        booking.setPaymentStatus("PAID".equalsIgnoreCase(request.getPaymentMethod()) ? "PAID" : "PENDING");
        booking.setStatus(Booking.Status.BOOKED);

        if (request.getItems() != null) {
            for (BookingItemRequest itemReq : request.getItems()) {
                BookingItem item = new BookingItem(itemReq.getTitle(), itemReq.getPrice(), itemReq.getQuantity(), booking);
                booking.getItems().add(item);
            }
        }

        Booking savedBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(savedBooking);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateBookingStatus(@PathVariable Long id, @RequestParam("status") String statusStr) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Booking booking = bookingOpt.get();
        try {
            Booking.Status newStatus = Booking.Status.valueOf(statusStr.toUpperCase());
            booking.setStatus(newStatus);
            booking.setUpdatedAt(LocalDateTime.now());
            bookingRepository.save(booking);
            return ResponseEntity.ok(booking);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid booking status: " + statusStr);
        }
    }
}
