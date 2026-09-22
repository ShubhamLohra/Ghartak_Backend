package com.ghartak.config;

import com.ghartak.model.*;
import com.ghartak.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ServiceCategoryRepository categoryRepository;

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RawMaterialRepository rawMaterialRepository;

    @Autowired
    private ServiceLeadRepository leadRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private AdminNotificationRepository notificationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() > 0) return;

        System.out.println(">>> Initializing Ghar Tak Database with Services & Seed Data...");

        // Clear orphan service items if categories were recreated
        serviceItemRepository.deleteAll();

        // 1. Seed Users (Safely check if existing before save)
        User admin = userRepository.findByEmail("admin@ghartak.com").orElseGet(() -> 
            userRepository.save(new User("admin@ghartak.com", passwordEncoder.encode("admin123"), "GharTak Admin", "9876543210", "GharTak HQ, Main Road", "Hazaribagh", "825301", Role.ADMIN))
        );

        User customer = userRepository.findByEmail("user@ghartak.com").orElseGet(() -> 
            userRepository.save(new User("user@ghartak.com", passwordEncoder.encode("user123"), "Shubham Kumar", "9811223344", "Flat 402, Lake View Apartments, Matwari", "Hazaribagh", "825301", Role.CUSTOMER))
        );

        User worker1 = userRepository.findByEmail("rajesh.electric@ghartak.com").orElseGet(() -> {
            User w = new User("rajesh.electric@ghartak.com", passwordEncoder.encode("worker123"), "Rajesh Kumar", "9988776655", "Korrah Chowk", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
            w.setProfession("Electrician Services");
            w.setDesignation("Senior Electrician");
            w.setRating(4.9);
            w.setCompletedJobs(142);
            w.setTotalEarnings(63900.0);
            w.setDailyLeadsRemaining(3);
            w.setCreatedAt(LocalDateTime.now().minusDays(180));
            return userRepository.save(w);
        });

        User worker2 = userRepository.findByEmail("afrin.ceiling@ghartak.com").orElseGet(() -> {
            User w = new User("afrin.ceiling@ghartak.com", passwordEncoder.encode("worker123"), "Afrin Khan", "9955443322", "Boddom Bazar", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
            w.setProfession("Interior & False Ceiling");
            w.setDesignation("False Ceiling Specialist");
            w.setRating(4.95);
            w.setCompletedJobs(89);
            w.setTotalEarnings(124500.0);
            w.setDailyLeadsRemaining(3);
            w.setCreatedAt(LocalDateTime.now().minusDays(120));
            return userRepository.save(w);
        });

        User laundryWorker1 = userRepository.findByEmail("ramesh.laundry@ghartak.com").orElseGet(() -> {
            User w = new User("ramesh.laundry@ghartak.com", passwordEncoder.encode("worker123"), "Ramesh Sharma", "9871122334", "Call Babu Chowk", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
            w.setProfession("Laundry & Dry Cleaning");
            w.setDesignation("Master Laundry Partner");
            w.setRating(4.9);
            w.setCompletedJobs(178);
            w.setTotalEarnings(53400.0);
            w.setDailyLeadsRemaining(3);
            w.setCreatedAt(LocalDateTime.now().minusDays(210));
            return userRepository.save(w);
        });

        User laundryWorker2 = userRepository.findByEmail("sita.laundry@ghartak.com").orElseGet(() -> {
            User w = new User("sita.laundry@ghartak.com", passwordEncoder.encode("worker123"), "Sita Verma (Express Steam Press Specialist)", "9872233445", "Canary Hill Road", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
            w.setProfession("Laundry & Dry Cleaning");
            w.setRating(4.85);
            w.setCompletedJobs(94);
            w.setTotalEarnings(28200.0);
            w.setDailyLeadsRemaining(3);
            w.setCreatedAt(LocalDateTime.now().minusDays(90));
            return userRepository.save(w);
        });

        User plumberWorker = userRepository.findByEmail("suresh.plumber@ghartak.com").orElseGet(() -> {
            User w = new User("suresh.plumber@ghartak.com", passwordEncoder.encode("worker123"), "Suresh Plumbing Works", "9873344556", "Demotand Area", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
            w.setProfession("Plumbing Services");
            w.setRating(4.8);
            w.setCompletedJobs(112);
            w.setTotalEarnings(44800.0);
            w.setDailyLeadsRemaining(3);
            w.setCreatedAt(LocalDateTime.now().minusDays(150));
            return userRepository.save(w);
        });

        User supplier = userRepository.findByEmail("supplier@ghartak.com").orElseGet(() -> 
            userRepository.save(new User("supplier@ghartak.com", passwordEncoder.encode("supplier123"), "GharTak Hardware Depot Owner", "9899001122", "Industrial Estate, Pagmil", "Hazaribagh", "825301", Role.SUPPLIER))
        );

        // 2. Seed All Service Categories & Base Charges / Commission Rules
        ServiceCategory electric = new ServiceCategory("Electrician Services", "ELECTRIC", "House electrician, ceiling fans, wiring, MCB & switchboard", 149.0, 15.0, "PERCENTAGE");
        categoryRepository.save(electric);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Ceiling Fan Repair & Installation", "Diagnostic & repair of fan motor, regulator replacement, or new fan mounting", 199.0, 299.0, "30 mins", 4.9, 320, "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?auto=format&fit=crop&w=600&q=80", "per unit", true, electric),
            new ServiceItem("Full House Electric Checkup & Wiring Fix", "Comprehensive checkup of short circuits, main MCB box, and socket replacement", 499.0, 799.0, "2 Hours", 4.8, 210, "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?auto=format&fit=crop&w=600&q=80", "per visit", false, electric),
            new ServiceItem("MCB & Switchboard Upgrade", "Replacement of old fuse boxes with modern trip MCB switches for safety", 349.0, 499.0, "45 mins", 4.9, 180, "https://images.unsplash.com/photo-1544725176-7c40e5a71c5e?auto=format&fit=crop&w=600&q=80", "per board", true, electric)
        ));

        ServiceCategory carpenter = new ServiceCategory("Carpenter Services", "CARPENTER", "Furniture repair, door latch, modular kitchen & custom woodwork", 199.0, 15.0, "PERCENTAGE");
        categoryRepository.save(carpenter);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Door Lock & Latch Installation", "High-security lock fitting, handle repair, and alignment", 249.0, 399.0, "30 mins", 4.8, 150, "https://images.unsplash.com/photo-1517646287270-a5a9ca602e5c?auto=format&fit=crop&w=600&q=80", "per door", true, carpenter),
            new ServiceItem("Modular Kitchen Drawer & Hinge Repair", "Hydraulic soft-close hinge replacement and drawer track fixes", 399.0, 599.0, "1 Hour", 4.9, 95, "https://images.unsplash.com/photo-1556911220-e15b29be8c8f?auto=format&fit=crop&w=600&q=80", "per cabinet", false, carpenter)
        ));

        ServiceCategory plumber = new ServiceCategory("Plumbing Services", "PLUMBER", "Tap repair, pipe leakages, drain blockage & water tank deep cleaning", 149.0, 15.0, "PERCENTAGE");
        categoryRepository.save(plumber);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Tap & Shower Leakage Repair", "Washer replacement, cartridge fix, faucet replacement", 149.0, 249.0, "20 mins", 4.9, 580, "https://images.unsplash.com/photo-1585704032915-c3400ca199e7?auto=format&fit=crop&w=600&q=80", "per faucet", true, plumber),
            new ServiceItem("Drain & Basin Blockage Clearance", "High pressure spring clearance for kitchen sink or bathroom drain", 299.0, 449.0, "30 mins", 4.8, 410, "https://images.unsplash.com/photo-1507089947368-19c1da9775ae?auto=format&fit=crop&w=600&q=80", "per line", true, plumber)
        ));

        ServiceCategory building = new ServiceCategory("Building Repair & Construction", "BUILDING_REPAIR", "Hire certified Labour, Mistry, Contractor, Engineer", 499.0, 10.0, "PERCENTAGE");
        categoryRepository.save(building);

        ServiceCategory laundry = new ServiceCategory("Laundry & Dry Cleaning", "LAUNDRY", "Doorstep pickup for wash & fold, suit dry cleaning, steam press & shoe spa", 149.0, 15.0, "PERCENTAGE");
        categoryRepository.save(laundry);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Wash & Fold", "Eco-friendly detergent wash and neat fold", 80.0, 100.0, "24 Hours", 4.9, 140, "https://images.unsplash.com/photo-1517677208171-0bc6725a3e60?auto=format&fit=crop&w=600&q=80", "per kg", true, laundry),
            new ServiceItem("Wash + Iron", "Deep wash with crisp steam press ironing", 120.0, 150.0, "24 Hours", 4.8, 98, "https://images.unsplash.com/photo-1489274495757-95c7c837b101?auto=format&fit=crop&w=600&q=80", "per kg", true, laundry),
            new ServiceItem("Dry Clean Shirt", "Gentle solvent dry cleaning & premium hanger packaging", 100.0, 140.0, "48 Hours", 4.9, 210, "https://images.unsplash.com/photo-1598033129183-c4f50c736f10?auto=format&fit=crop&w=600&q=80", "per piece", false, laundry),
            new ServiceItem("Dry Clean Suit", "2-Piece or 3-Piece suit dry cleaning with stain treatment", 300.0, 400.0, "48 Hours", 4.9, 310, "https://images.unsplash.com/photo-1594938298603-c8148c4dae35?auto=format&fit=crop&w=600&q=80", "per piece", true, laundry)
        ));

        // 3. Seed Raw Materials
        rawMaterialRepository.saveAll(Arrays.asList(
            new RawMaterialProduct("UltraTech Cement 50kg", "Cement", "GharTak Authorized Store", 385.0, "bag", 4.9, true, 10, "Grade 53 PPC Weather Shield Cement", "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?auto=format&fit=crop&w=600&q=80"),
            new RawMaterialProduct("Tata Tiscon TMT Steel 12mm", "Steel TMT", "Shree Ram Steel Corp", 62.0, "kg", 4.95, true, 100, "Fe-550SD Grade Ductile Steel Bars", "https://images.unsplash.com/photo-1504307651254-35680f356dfd?auto=format&fit=crop&w=600&q=80")
        ));

        // 4. Seed Reviews
        reviewRepository.saveAll(Arrays.asList(
            new Review("Amit Verma", "Matwari, Hazaribagh", 5.0, "Laundry & Dry Cleaning", "Ramesh Sharma did a fantastic job with our suit dry cleaning in Matwari! Delivered on time, perfectly steam pressed."),
            new Review("Priya Sharma", "Korrah, Hazaribagh", 5.0, "Electrician Services", "Booked fan installation on Ghar Tak app in morning, electrician arrived in 20 minutes at Korrah Chowk! Very polite & expert.")
        ));

        // 5. Seed Bookings with Commission & Provider Net Payout
        Booking b1 = new Booking();
        b1.setBookingCode("GT-982101");
        b1.setCustomer(customer);
        b1.setProvider(laundryWorker1);
        b1.setServiceCategoryName("Laundry & Dry Cleaning");
        b1.setScheduledDate(LocalDateTime.now().plusHours(1));
        b1.setScheduledTimeSlot("10:00 AM - 12:00 PM");
        b1.setAddress("Flat 402, Lake View Apartments, Matwari");
        b1.setCity("Hazaribagh");
        b1.setPincode("825301");
        b1.setContactPhone("9811223344");
        b1.setTotalAmount(648.0);
        b1.setTaxesAndFee(49.0);
        b1.setCommissionAmount(97.2); // 15% commission
        b1.setProviderPayout(550.8);
        b1.setPaymentMethod("UPI Payment");
        b1.setPaymentStatus("PAID");
        b1.setStatus(Booking.Status.IN_PROGRESS);
        bookingRepository.save(b1);

        Booking b2 = new Booking();
        b2.setBookingCode("GT-982102");
        b2.setCustomer(customer);
        b2.setProvider(worker1);
        b2.setServiceCategoryName("Electrician Services");
        b2.setScheduledDate(LocalDateTime.now().plusHours(2));
        b2.setScheduledTimeSlot("02:00 PM - 04:00 PM");
        b2.setAddress("House No 88, Near Canary Hill Road, Korrah");
        b2.setCity("Hazaribagh");
        b2.setPincode("825301");
        b2.setContactPhone("9899112233");
        b2.setTotalAmount(499.0);
        b2.setTaxesAndFee(49.0);
        b2.setCommissionAmount(74.85);
        b2.setProviderPayout(424.15);
        b2.setPaymentMethod("Cash on Delivery");
        b2.setPaymentStatus("PENDING");
        b2.setStatus(Booking.Status.EN_ROUTE);
        bookingRepository.save(b2);

        // Booking 3: Cancelled Booking
        Booking b3 = new Booking();
        b3.setBookingCode("GT-982103");
        b3.setCustomer(customer);
        b3.setProvider(plumberWorker);
        b3.setServiceCategoryName("Plumbing Services");
        b3.setScheduledDate(LocalDateTime.now().minusDays(2));
        b3.setScheduledTimeSlot("11:00 AM - 01:00 PM");
        b3.setAddress("House 12, Boddom Bazar");
        b3.setCity("Hazaribagh");
        b3.setPincode("825301");
        b3.setContactPhone("9877112233");
        b3.setTotalAmount(399.0);
        b3.setTaxesAndFee(49.0);
        b3.setCommissionAmount(59.85);
        b3.setProviderPayout(339.15);
        b3.setPaymentMethod("UPI Payment");
        b3.setPaymentStatus("REFUNDED");
        b3.setStatus(Booking.Status.CANCELLED);
        b3.setCancelStage("EN_ROUTE");
        b3.setCancellationReason("Customer had urgent travel out of station");
        b3.setCancelledBy("CUSTOMER");
        bookingRepository.save(b3);

        // 6. Seed In-App Admin Notifications
        notificationRepository.save(new AdminNotification("New Booking Created", "Booking GT-982101 was placed for Laundry & Dry Cleaning.", "BOOKING_CREATED", b1.getId()));
        notificationRepository.save(new AdminNotification("Provider Assigned", "Rajesh Kumar was assigned to Booking GT-982102.", "ASSIGNMENT", b2.getId()));
        notificationRepository.save(new AdminNotification("Booking Cancelled", "Booking GT-982103 was cancelled at stage EN_ROUTE.", "CANCELLATION", b3.getId()));

        System.out.println(">>> Seed Data successfully loaded for Ghar Tak Backend!");
    }
}
