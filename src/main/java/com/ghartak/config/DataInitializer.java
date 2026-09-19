package com.ghartak.config;

import com.ghartak.model.*;
import com.ghartak.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() > 0) return;

        System.out.println(">>> Initializing Ghar Tak Database with Services & Seed Data...");

        // 1. Seed Users
        User admin = new User("admin@ghartak.com", passwordEncoder.encode("admin123"), "GharTak Admin", "9876543210", "GharTak HQ, Main Road", "Hazaribagh", "825301", Role.ADMIN);
        userRepository.save(admin);

        User customer = new User("user@ghartak.com", passwordEncoder.encode("user123"), "Shubham Kumar", "9811223344", "Flat 402, Lake View Apartments, Matwari", "Hazaribagh", "825301", Role.CUSTOMER);
        userRepository.save(customer);

        User worker1 = new User("rajesh.electric@ghartak.com", passwordEncoder.encode("worker123"), "Rajesh Kumar (Senior Electrician)", "9988776655", "Korrah Chowk", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
        worker1.setProfession("Electrician Services");
        worker1.setRating(4.9);
        worker1.setCompletedJobs(142);
        worker1.setTotalEarnings(63900.0);
        worker1.setDailyLeadsRemaining(3);
        worker1.setCreatedAt(LocalDateTime.now().minusDays(180));
        userRepository.save(worker1);

        User worker2 = new User("afrin.ceiling@ghartak.com", passwordEncoder.encode("worker123"), "Afrin Khan (False Ceiling Specialist)", "9955443322", "Boddom Bazar", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
        worker2.setProfession("Interior & False Ceiling");
        worker2.setRating(4.95);
        worker2.setCompletedJobs(89);
        worker2.setTotalEarnings(124500.0);
        worker2.setDailyLeadsRemaining(3);
        worker2.setCreatedAt(LocalDateTime.now().minusDays(120));
        userRepository.save(worker2);

        // Laundry Partners Enrolled
        User laundryWorker1 = new User("ramesh.laundry@ghartak.com", passwordEncoder.encode("worker123"), "Ramesh Sharma (Master Laundry Partner)", "9871122334", "Call Babu Chowk", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
        laundryWorker1.setProfession("Laundry & Dry Cleaning");
        laundryWorker1.setRating(4.9);
        laundryWorker1.setCompletedJobs(178);
        laundryWorker1.setTotalEarnings(53400.0);
        laundryWorker1.setDailyLeadsRemaining(3);
        laundryWorker1.setCreatedAt(LocalDateTime.now().minusDays(210));
        userRepository.save(laundryWorker1);

        User laundryWorker2 = new User("sita.laundry@ghartak.com", passwordEncoder.encode("worker123"), "Sita Verma (Express Steam Press Specialist)", "9872233445", "Canary Hill Road", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
        laundryWorker2.setProfession("Laundry & Dry Cleaning");
        laundryWorker2.setRating(4.85);
        laundryWorker2.setCompletedJobs(94);
        laundryWorker2.setTotalEarnings(28200.0);
        laundryWorker2.setDailyLeadsRemaining(3);
        laundryWorker2.setCreatedAt(LocalDateTime.now().minusDays(90));
        userRepository.save(laundryWorker2);

        User plumberWorker = new User("suresh.plumber@ghartak.com", passwordEncoder.encode("worker123"), "Suresh Plumbing Works", "9873344556", "Demotand Area", "Hazaribagh", "825301", Role.SERVICE_PROVIDER);
        plumberWorker.setProfession("Plumbing Services");
        plumberWorker.setRating(4.8);
        plumberWorker.setCompletedJobs(112);
        plumberWorker.setTotalEarnings(44800.0);
        plumberWorker.setDailyLeadsRemaining(3);
        plumberWorker.setCreatedAt(LocalDateTime.now().minusDays(150));
        userRepository.save(plumberWorker);

        User supplier = new User("supplier@ghartak.com", passwordEncoder.encode("supplier123"), "GharTak Hardware Depot Owner", "9899001122", "Industrial Estate, Pagmil", "Hazaribagh", "825301", Role.SUPPLIER);
        userRepository.save(supplier);

        // 2. Seed All 11 Service Categories & Sub-items (matching handwritten notes & logo)

        // Category 1: Electrician
        ServiceCategory electric = new ServiceCategory("Electrician Services", "ELECTRIC", "Zap", "House electrician, ceiling fans, wiring, MCB & switchboard", "02 + 01", "Hardware & Electrical", "from-amber-500 to-yellow-600");
        categoryRepository.save(electric);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Ceiling Fan Repair & Installation", "Diagnostic & repair of fan motor, regulator replacement, or new fan mounting", 199.0, 299.0, "30 mins", 4.9, 320, "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?auto=format&fit=crop&w=600&q=80", "per unit", true, electric),
            new ServiceItem("Full House Electric Checkup & Wiring Fix", "Comprehensive inspection of short circuits, main MCB box, and socket replacement", 499.0, 799.0, "2 Hours", 4.8, 210, "https://images.unsplash.com/photo-1558494949-ef010cbdcc31?auto=format&fit=crop&w=600&q=80", "per visit", false, electric),
            new ServiceItem("MCB & Switchboard Upgrade", "Replacement of old fuse boxes with modern trip MCB switches for safety", 349.0, 499.0, "45 mins", 4.9, 180, "https://images.unsplash.com/photo-1544725176-7c40e5a71c5e?auto=format&fit=crop&w=600&q=80", "per board", true, electric)
        ));

        // Category 2: Carpenter
        ServiceCategory carpenter = new ServiceCategory("Carpenter Services", "CARPENTER", "Hammer", "Furniture repair, door latch, modular kitchen & custom woodwork", "01 + 01", "Hardware & Carpentry", "from-orange-500 to-amber-600");
        categoryRepository.save(carpenter);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Door Lock & Latch Installation", "High-security lock fitting, handle repair, and alignment", 249.0, 399.0, "30 mins", 4.8, 150, "https://images.unsplash.com/photo-1517646287270-a5a9ca602e5c?auto=format&fit=crop&w=600&q=80", "per door", true, carpenter),
            new ServiceItem("Modular Kitchen Drawer & Hinge Repair", "Hydraulic soft-close hinge replacement and drawer track fixes", 399.0, 599.0, "1 Hour", 4.9, 95, "https://images.unsplash.com/photo-1556911220-e15b29be8c8f?auto=format&fit=crop&w=600&q=80", "per cabinet", false, carpenter),
            new ServiceItem("Bed & Furniture Assembly", "Wooden bed frame assembly, wardrobe dismantling, or custom shelf fitting", 499.0, 799.0, "1.5 Hours", 4.85, 230, "https://images.unsplash.com/photo-1538688525198-9b88f6f53126?auto=format&fit=crop&w=600&q=80", "per piece", true, carpenter)
        ));

        // Category 3: Car / Bike Repair
        ServiceCategory vehicle = new ServiceCategory("Car & Bike Repair", "VEHICLE_REPAIR", "Wrench", "Doorstep car servicing, bike oil change, brake check & jumpstart", "01", "Vehicle Services", "from-yellow-500 to-amber-700");
        categoryRepository.save(vehicle);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Doorstep Bike Service & Oil Change", "Complete engine oil refresh, spark plug cleaning, chain lube, brake adjustment", 399.0, 599.0, "45 mins", 4.9, 410, "https://images.unsplash.com/photo-1558981806-ec527fa84c39?auto=format&fit=crop&w=600&q=80", "per bike", true, vehicle),
            new ServiceItem("Car Battery Jumpstart & Diagnostic", "Emergency battery boost, alternator voltage check, doorstep arrival", 299.0, 499.0, "20 mins", 4.95, 300, "https://images.unsplash.com/photo-1486006920555-c77dce18193b?auto=format&fit=crop&w=600&q=80", "per car", true, vehicle)
        ));

        // Category 4: Paint
        ServiceCategory paint = new ServiceCategory("Painting & Waterproofing", "PAINT", "Paintbrush", "Interior wall paint, exterior coat, touchup & waterproof sealing", "01", "Home Renovation", "from-amber-600 to-yellow-500");
        categoryRepository.save(paint);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Single Wall Accent Painting", "Designer wall finish, emulsion coat, mask taping included", 999.0, 1499.0, "3 Hours", 4.9, 180, "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?auto=format&fit=crop&w=600&q=80", "per wall", true, paint),
            new ServiceItem("Waterproof Roof Sealing & Patching", "Anti-seepage coating, crack filling with elastomeric sealer", 1499.0, 2199.0, "4 Hours", 4.8, 140, "https://images.unsplash.com/photo-1562259949-e8e7689d7828?auto=format&fit=crop&w=600&q=80", "per 100 sqft", false, paint)
        ));

        // Category 5: Building Repair (Special Professional Types: Labour 02, Mistry 02, Contractor 01, Engineer 04)
        ServiceCategory building = new ServiceCategory("Building Repair & Construction", "BUILDING_REPAIR", "Building", "Hire certified Labour (02), Mistry (02), Contractor (01), Engineer (04)", "Labour 02 | Mistry 02 | Contractor 01 | Engineer 04", "Construction & Structural", "from-yellow-600 to-orange-600");
        categoryRepository.save(building);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Daily Helper Labour (02 Skilled Workers)", "Heavy lifting, material movement, site cleanup & masonry assistance", 850.0, 1100.0, "8 Hours", 4.8, 520, "https://images.unsplash.com/photo-1504307651254-35680f356dfd?auto=format&fit=crop&w=600&q=80", "per day", true, building),
            new ServiceItem("Master Mason / Mistry (02 Specialists)", "Plastering, brickwork, tile fixing, beam patching & masonry repair", 1450.0, 1800.0, "8 Hours", 4.9, 430, "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?auto=format&fit=crop&w=600&q=80", "per day", true, building),
            new ServiceItem("General Building Contractor (01 Lead)", "Complete site supervision, material procurement management & execution plan", 2500.0, 3500.0, "Per Day Visit", 4.95, 190, "https://images.unsplash.com/photo-1503387762-592deb58ef4e?auto=format&fit=crop&w=600&q=80", "per visit", false, building),
            new ServiceItem("Chartered Civil Engineer Inspection (04 Expert Consultants)", "Structural audit, load assessment, blueprint review & safety certification", 3499.0, 4999.0, "Consultation", 5.0, 110, "https://images.unsplash.com/photo-1581094794329-c8112a89af12?auto=format&fit=crop&w=600&q=80", "per project", true, building)
        ));

        // Category 6: CCTV & Computer Services
        ServiceCategory cctv = new ServiceCategory("CCTV & Computer Services", "CCTV_COMPUTER", "Camera", "CCTV installation, desktop/laptop repair, WiFi router & network setup", "01", "Tech & Security", "from-yellow-500 to-amber-500");
        categoryRepository.save(cctv);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("CCTV Camera Setup & Mobile Viewing Config", "IP camera mounting, DVR wiring setup, mobile app live feed sync", 599.0, 899.0, "1.5 Hours", 4.9, 290, "https://images.unsplash.com/photo-1557597774-9d273605dfa9?auto=format&fit=crop&w=600&q=80", "per camera", true, cctv),
            new ServiceItem("Computer & Laptop On-site Repair", "Windows reinstallation, RAM upgrade, SSD installation, blue screen fix", 499.0, 699.0, "1 Hour", 4.85, 210, "https://images.unsplash.com/photo-1588872657578-7efd1f1555ed?auto=format&fit=crop&w=600&q=80", "per PC", true, cctv)
        ));

        // Category 7: Raw Material Supplier
        ServiceCategory rawMaterial = new ServiceCategory("Raw Material (Supplier Depot)", "RAW_MATERIAL", "Package", "Direct store orders for Cement, TMT Steel, Sand, Bricks & Plumbing", "Owner / Supplier Direct", "Material Marketplace", "from-amber-600 to-orange-700");
        categoryRepository.save(rawMaterial);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("UltraTech Weather Plus Cement (50kg Bag)", "Grade 53 PPC high-strength construction cement", 385.0, 420.0, "Instant Delivery", 4.9, 850, "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?auto=format&fit=crop&w=600&q=80", "per bag", true, rawMaterial),
            new ServiceItem("Tata Tiscon 550SD TMT Rebar Steel (12mm)", "Fe-550SD earthquake resistant structural steel bar", 62.0, 68.0, "Same Day", 4.95, 620, "https://images.unsplash.com/photo-1504307651254-35680f356dfd?auto=format&fit=crop&w=600&q=80", "per kg", true, rawMaterial),
            new ServiceItem("Red Clay Solid Bricks (First Class 1000 Pcs)", "Kiln burnt red bricks for structural load bearing walls", 7500.0, 8500.0, "1 Day", 4.8, 310, "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?auto=format&fit=crop&w=600&q=80", "per 1000 pcs", false, rawMaterial)
        ));

        // Category 8: Plumber
        ServiceCategory plumber = new ServiceCategory("Plumbing Services", "PLUMBER", "Droplet", "Tap repair, pipe leakages, drain blockage & water tank deep cleaning", "01", "Hardware & Plumbing", "from-yellow-400 to-amber-600");
        categoryRepository.save(plumber);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Tap & Shower Leakage Repair", "Washer replacement, cartridge fix, faucet replacement", 149.0, 249.0, "20 mins", 4.9, 580, "https://images.unsplash.com/photo-1585704032915-c3400ca199e7?auto=format&fit=crop&w=600&q=80", "per faucet", true, plumber),
            new ServiceItem("Drain & Basin Blockage Clearance", "High pressure spring clearance for kitchen sink or bathroom drain", 299.0, 449.0, "30 mins", 4.8, 410, "https://images.unsplash.com/photo-1507089947368-19c1da9775ae?auto=format&fit=crop&w=600&q=80", "per line", true, plumber),
            new ServiceItem("Overhead Water Tank Deep Sanitization", "Automatic sludge pump cleaning, UV treatment for 1000L tank", 699.0, 999.0, "1.5 Hours", 4.95, 190, "https://images.unsplash.com/photo-1542013936693-884638332954?auto=format&fit=crop&w=600&q=80", "per tank", false, plumber)
        ));

        // Category 9: Washroom / Toilet Cleaner
        ServiceCategory washroom = new ServiceCategory("Washroom & Toilet Cleaning", "WASHROOM_CLEANING", "Sparkles", "Deep bathroom scrubbing, hard water stain removal & sanitization", "02", "Sanitisation & Cleaning", "from-amber-500 to-yellow-400");
        categoryRepository.save(washroom);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Deep Bathroom Scrubbing & Tile Descaling", "Removal of yellow hard water stains, grout bleaching, mirror polishing", 499.0, 699.0, "1 Hour", 4.9, 670, "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?auto=format&fit=crop&w=600&q=80", "per bathroom", true, washroom),
            new ServiceItem("Commode & Jet Spray Anti-Bacterial Sanitization", "Sanitizing seat rim, acid wash & jet pressure nozzle fix", 299.0, 399.0, "30 mins", 4.85, 320, "https://images.unsplash.com/photo-1585704032915-c3400ca199e7?auto=format&fit=crop&w=600&q=80", "per unit", false, washroom)
        ));

        // Category 10: Interior Work / False Ceiling
        ServiceCategory falseCeiling = new ServiceCategory("Interior & False Ceiling", "FALSE_CEILING", "Layout", "POP & Gypsum false ceiling design, ambient cove LED lighting (Afrin Team)", "01 (Specialist Afrin)", "Interior Design", "from-yellow-500 to-amber-600");
        categoryRepository.save(falseCeiling);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Gypsum Board Designer False Ceiling", "Saint-Gobain gypsum board, galvanized perimeter channel, seamless putty finish", 85.0, 110.0, "3 Days", 4.95, 140, "https://images.unsplash.com/photo-1600585154340-be6161a56a0c?auto=format&fit=crop&w=600&q=80", "per sqft", true, falseCeiling),
            new ServiceItem("Ambient Cove & Spot Light Profile Installation", "Diffuser aluminum channel, warm 3000K LED strip mounting in ceiling cove", 120.0, 160.0, "1 Day", 4.9, 95, "https://images.unsplash.com/photo-1513694203232-719a280e022f?auto=format&fit=crop&w=600&q=80", "per running ft", true, falseCeiling)
        ));

        // Category 11: Diagnostic Related Services
        ServiceCategory diagnostic = new ServiceCategory("Diagnostic & Health Checkups", "DIAGNOSTIC", "Activity", "At-home sample collection, blood test packages, CBC, Diabetes panel", "01 (Doorstep Visit)", "Health & Wellness", "from-amber-600 to-yellow-600");
        categoryRepository.save(diagnostic);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("Full Body Comprehensive Health Package", "63 Essential Tests including Lipid Profile, Kidney Function, Thyroid & Blood Sugar", 799.0, 1999.0, "Morning Slot", 4.95, 890, "https://images.unsplash.com/photo-1579154204601-01588f351e67?auto=format&fit=crop&w=600&q=80", "per person", true, diagnostic),
            new ServiceItem("HbA1c & Fasting Diabetes Monitor Panel", "Accurate glycated hemoglobin blood test with digital report within 12 hours", 349.0, 599.0, "30 mins", 4.9, 450, "https://images.unsplash.com/photo-1532938911079-1b06ac7ceec7?auto=format&fit=crop&w=600&q=80", "per sample", false, diagnostic)
        ));

        // Category 12: Laundry & Dry Cleaning
        ServiceCategory laundry = new ServiceCategory("Laundry & Dry Cleaning", "LAUNDRY", "Shirt", "Doorstep pickup for 5kg wash & fold, suit dry cleaning, steam press & shoe spa", "02 Partners Enrolled", "Home Care", "from-indigo-500 to-sky-600");
        categoryRepository.save(laundry);
        serviceItemRepository.saveAll(Arrays.asList(
            new ServiceItem("5kg Wash & Fold Express Laundry", "Antiseptic washing, fabric softener, doorstep pickup & fold packaging", 249.0, 399.0, "24 Hours", 4.9, 410, "https://images.unsplash.com/photo-1545173168-9f1947eebb7f?auto=format&fit=crop&w=600&q=80", "per 5kg load", true, laundry),
            new ServiceItem("Suit & Premium Coat Dry Cleaning", "Organic dry cleaning stain treatment, steam press & protective hanger bag", 399.0, 599.0, "48 Hours", 4.95, 290, "https://images.unsplash.com/photo-1517677208171-0bc6725a3e60?auto=format&fit=crop&w=600&q=80", "per suit", true, laundry),
            new ServiceItem("Steam Press & Ironing (Set of 5 Shirts)", "Wrinkle-free high pressure steam press for formal shirts & trousers", 149.0, 249.0, "Same Day", 4.85, 530, "https://images.unsplash.com/photo-1517677208171-0bc6725a3e60?auto=format&fit=crop&w=600&q=80", "per 5 items", false, laundry)
        ));

        // 3. Seed Raw Material Products
        rawMaterialRepository.saveAll(Arrays.asList(
            new RawMaterialProduct("UltraTech Cement 50kg", "Cement", "GharTak Authorized Store", 385.0, "bag", 4.9, true, 10, "Grade 53 PPC Weather Shield Cement", "https://images.unsplash.com/photo-1589939705384-5185137a7f0f?auto=format&fit=crop&w=600&q=80"),
            new RawMaterialProduct("Tata Tiscon TMT Steel 12mm", "Steel TMT", "Shree Ram Steel Corp", 62.0, "kg", 4.95, true, 100, "Fe-550SD Grade Ductile Steel Bars", "https://images.unsplash.com/photo-1504307651254-35680f356dfd?auto=format&fit=crop&w=600&q=80"),
            new RawMaterialProduct("Yamuna River Sand (100 cu.ft)", "Sand", "Balaji Sand Supplier", 4500.0, "brass", 4.8, true, 1, "Washed coarse river sand for concrete slab work", "https://images.unsplash.com/photo-1508873696983-2df515122519?auto=format&fit=crop&w=600&q=80")
        ));

        // 4. Seed Provider Leads
        leadRepository.saveAll(Arrays.asList(
            new ServiceLead("LEAD-901", "False Ceiling Specialist", "Afrin Customer Job", "9812345678", "South Ext Part 2", "1.8 km", 450.0, "Today 2:00 PM", "Living room 18x12 ft POP cove false ceiling design quote", "AVAILABLE"),
            new ServiceLead("LEAD-902", "Electrician Services", "Kapil Dev", "9823456789", "Indirapuram Sector 4", "3.1 km", 450.0, "Today 4:00 PM", "MCB tripping issue & 3 ceiling fan regulators replacement", "AVAILABLE"),
            new ServiceLead("LEAD-903", "Building Repair & Construction", "Sharma Villa", "9834567890", "Noida Sector 62", "2.5 km", 450.0, "Tomorrow 10:00 AM", "Requirement for 2 Mistry + 2 Labour for 2-day balcony plastering", "AVAILABLE")
        ));

        // 5. Seed Reviews
        reviewRepository.saveAll(Arrays.asList(
            new Review("Amit Verma", "Matwari, Hazaribagh", 5.0, "Laundry & Dry Cleaning", "Ramesh Sharma did a fantastic job with our suit dry cleaning in Matwari! Delivered on time, perfectly steam pressed."),
            new Review("Priya Sharma", "Korrah, Hazaribagh", 5.0, "Electrician Services", "Booked fan installation on Ghar Tak app in morning, electrician arrived in 20 minutes at Korrah Chowk! Very polite & expert."),
            new Review("Ramesh Builder", "Boddom Bazar, Hazaribagh", 4.9, "Building Repair & Construction", "Ordered 2 Mistry & 2 Labour through Ghar Tak app in Boddom Bazar. Super efficient work and verified local workers.")
        ));

        // 6. Seed Sample Bookings with Live Statuses
        
        // Booking 1: Laundry Service - IN_PROGRESS
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
        b1.setInstructions("Please handle woolens carefully during dry cleaning.");
        b1.setTotalAmount(648.0);
        b1.setTaxesAndFee(49.0);
        b1.setPaymentMethod("UPI Payment");
        b1.setPaymentStatus("PAID");
        b1.setStatus(Booking.Status.IN_PROGRESS);
        b1.getItems().add(new BookingItem("Suit & Premium Coat Dry Cleaning", 399.0, 1, b1));
        b1.getItems().add(new BookingItem("5kg Wash & Fold Express Laundry", 249.0, 1, b1));
        bookingRepository.save(b1);

        // Booking 2: Electrician Service - EN_ROUTE
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
        b2.setInstructions("MCB tripping constantly when AC is turned on.");
        b2.setTotalAmount(499.0);
        b2.setTaxesAndFee(49.0);
        b2.setPaymentMethod("Cash on Delivery");
        b2.setPaymentStatus("PENDING");
        b2.setStatus(Booking.Status.EN_ROUTE);
        b2.getItems().add(new BookingItem("Full House Electric Checkup & Wiring Fix", 499.0, 1, b2));
        bookingRepository.save(b2);

        // Booking 3: Building Repair - COMPLETED
        Booking b3 = new Booking();
        b3.setBookingCode("GT-982103");
        b3.setCustomer(customer);
        b3.setProvider(worker2);
        b3.setServiceCategoryName("Building Repair & Construction");
        b3.setScheduledDate(LocalDateTime.now().minusDays(1));
        b3.setScheduledTimeSlot("09:00 AM - 05:00 PM");
        b3.setAddress("House No. 12, Boddom Bazar Main Road");
        b3.setCity("Hazaribagh");
        b3.setPincode("825301");
        b3.setContactPhone("9877112233");
        b3.setInstructions("Balcony plastering & safety column checkup.");
        b3.setTotalAmount(4600.0);
        b3.setTaxesAndFee(150.0);
        b3.setPaymentMethod("UPI Payment");
        b3.setPaymentStatus("PAID");
        b3.setStatus(Booking.Status.COMPLETED);
        b3.getItems().add(new BookingItem("Daily Helper Labour (02 Skilled Workers)", 850.0, 2, b3));
        b3.getItems().add(new BookingItem("Master Mason / Mistry (02 Specialists)", 1450.0, 2, b3));
        bookingRepository.save(b3);

        System.out.println(">>> Seed Data successfully loaded for Ghar Tak Backend!");
    }
}
