package com.kolak.reservation.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestApi {

    private UserRepo userRepo;

    @Autowired
    public UserRestApi(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String method() {
        return "Ziobro ty kurwo jebana";
    }
}
