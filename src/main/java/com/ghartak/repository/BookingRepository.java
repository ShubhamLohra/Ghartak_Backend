package com.ghartak.repository;

import com.ghartak.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    List<Booking> findByProviderIdOrderByCreatedAtDesc(Long providerId);
    List<Booking> findAllByOrderByCreatedAtDesc();
}
