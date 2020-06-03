package com.kolak.reservation;

import com.kolak.reservation.flight.FlightRepo;
import com.kolak.reservation.user.User;
import com.kolak.reservation.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Start {

    private UserRepo userRepo;
    private FlightRepo flightRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public Start(UserRepo userRepo, FlightRepo flightRepo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepo = userRepo;
        this.flightRepo = flightRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart() {

        provideAdmin();
        provideUser("User1", "User1");

    }

    public void provideAdmin() {
        long adminsInSystem = userRepo.findAll()
                .stream()
                .filter(user -> user.getRole().equals("ROLE_ADMIN"))
                .count();

        if (adminsInSystem < 1)
            userRepo.save(new User("admin", bCryptPasswordEncoder.encode("admin123"), "ROLE_ADMIN", "admin@admin.admin"));
    }

    public void provideUser(String username, String password) {
        long isThereUser = userRepo.findAll()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .count();

        if (isThereUser < 1)
        {
            userRepo.save(new User(username, bCryptPasswordEncoder.encode(password), "ROLE_USER", username + "@auser.com"));
            System.out.println(username + " added");
        } else {
            System.out.println(username + " already exists");
        }

    }
}
