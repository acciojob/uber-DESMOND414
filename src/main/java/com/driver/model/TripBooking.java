package com.driver.model;

import javax.persistence.*;
import java.util.Optional;

public class TripBooking {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tripBookingId;

    String fromLocation;
    String toLocation;

    int distanceInKm;

    public TripBooking(String fromLocation, String toLocation, int distanceInKm, TripStatus status, int bill) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.status = status;
        this.bill = bill;
    }

    public TripBooking(String fromLocation, String toLocation, int distanceInKm, TripStatus status) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.status = status;
    }

    public TripBooking() {
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    TripStatus status;

    int bill;

    public int getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(int tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @OneToMany(mappedBy = "tripBookingList", cascade = CascadeType.ALL)
    Customer customer;

    @ManyToOne
    Driver driver;
}