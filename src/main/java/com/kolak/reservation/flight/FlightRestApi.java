package com.kolak.reservation.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightRestApi {

    private FlightRepo flightRepo;

    @Autowired
    public FlightRestApi(FlightRepo flightRepo) {
        this.flightRepo = flightRepo;
    }

    @GetMapping("")
    public ResponseEntity<List<Flight>> getAllFlights() {
        return new ResponseEntity<List<Flight>>(HttpStatus.OK);
    }
}
