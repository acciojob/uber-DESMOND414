package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Driver {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    int driverId;

    public List<TripBooking> getTripBookings() {
        return tripBookings;
    }

    public void setTripBookings(List<TripBooking> tripBookings) {
        this.tripBookings = tripBookings;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    String mobile;

   String password;

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Driver() {
    }

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
 List<TripBooking> tripBookings=new ArrayList<>();

    @OneToOne
    Cab cab;
}