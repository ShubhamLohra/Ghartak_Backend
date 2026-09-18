package com.ghartak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GharTakApplication {
    public static void main(String[] args) {
        SpringApplication.run(GharTakApplication.class, args);
        System.out.println("=================================================");
        System.out.println("   Ghar Tak Backend Service Started Successfully! ");
        System.out.println("   Tagline: A to Z Solution in One Tap          ");
        System.out.println("   Server URL: http://localhost:8080            ");
        System.out.println("   H2 Console: http://localhost:8080/h2-console  ");
        System.out.println("=================================================");
    }
}
