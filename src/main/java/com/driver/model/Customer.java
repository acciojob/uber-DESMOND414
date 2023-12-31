package com.driver.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class Customer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int customerId;

    public Customer() {
    }

    String mobile;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<TripBooking> getTripBookingList() {
        return tripBookingList;
    }

    public void setTripBookingList(List<TripBooking> tripBookingList) {
        this.tripBookingList = tripBookingList;
    }

    public Customer(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    String password;

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

    @ManyToOne
   List<TripBooking> tripBookingList=new ArrayList<>();
}