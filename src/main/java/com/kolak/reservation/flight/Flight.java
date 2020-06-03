package com.kolak.reservation.flight;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightName;

    private ZonedDateTime departureDate;
    private ZonedDateTime arrivalDate;

    private String aircraftType;
    private String registration;
    private Country countryOfRegistration;
    private int productionYear;




}
