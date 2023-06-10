package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> customerOptional = customerRepository2.findById(customerId);
		customerOptional.ifPresent(customerRepository2::delete);

	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
//		List<Driver> availableDrivers = driverRepository2.findByCabAvailableIsTrueOrderByDriverIdAsc();
//
//		if (availableDrivers.isEmpty()) {
//			throw new Exception("No cab available!");
//		}
//
//		// Find the driver with the lowest driverId
//		Driver selectedDriver = availableDrivers.get(0);
//		for (Driver driver : availableDrivers) {
//			if (driver.getDriverId() < selectedDriver.getDriverId()) {
//				selectedDriver = driver;
//			}
//		}
//
//		// Create a new TripBooking and save it in the database
//		TripBooking tripBooking = new TripBooking(fromLocation, toLocation, distanceInKm, selectedDriver, customerId, TripStatus.BOOKED);
//		tripBookingRepository2.save(tripBooking);
//
//		// Update the selected driver's availability
//		selectedDriver.setCabAvailable(false);
//		driverRepository2.save(selectedDriver);
//
//		return tripBooking;
		List<Driver> availableDrivers = driverRepository2.findByCabAvailableIsTrueOrderByDriverIdAsc();

		if (availableDrivers.isEmpty()) {
			throw new Exception("No cab available!");
		}

		// Find the driver with the lowest driverId
		Driver selectedDriver = availableDrivers.get(0);
		for (Driver driver : availableDrivers) {
			if (driver.getDriverId() < selectedDriver.getDriverId()) {
				selectedDriver = driver;
			}
		}

		// Create a new TripBooking and save it in the database
//		TripBooking tripBooking = new TripBooking(fromLocation, toLocation, distanceInKm, selectedDriver, customerId, TripStatus.CONFIRMED);
		TripBooking tripBooking = new TripBooking(fromLocation, toLocation, distanceInKm,TripStatus.CONFIRMED);
		tripBooking.setDriver(selectedDriver);

		tripBooking.setCustomer(customerRepository2.findById(customerId).orElse(null));


		tripBookingRepository2.save(tripBooking);

		// Update the selected driver's availability

		selectedDriver.getCab().setAvailable(false);
		driverRepository2.save(selectedDriver);

		// Add the tripBooking to the driver's tripBookings
		selectedDriver.getTripBookings().add(tripBooking);
		driverRepository2.save(selectedDriver);

		return tripBooking;

	}

	@Override
	public void cancelTrip(Integer tripId) {
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> tripBookingOptional = tripBookingRepository2.findById(tripId);
		if (tripBookingOptional.isPresent()) {
			TripBooking tripBooking = tripBookingOptional.get();
			tripBooking.setStatus(TripStatus.CANCELED);

			// Update the driver's availability
			Driver driver = tripBooking.getDriver();
			driver.getCab().setAvailable(true);
			driverRepository2.save(driver);

			tripBookingRepository2.save(tripBooking);
		}

	}

	    @Override
	    public void completeTrip(Integer tripId )
		{
			Optional<TripBooking> tripBookingOptional = tripBookingRepository2.findById(tripId);
			if (tripBookingOptional.isPresent()) {
				TripBooking tripBooking = tripBookingOptional.get();
				tripBooking.setStatus(TripStatus.COMPLETED);

				// Update the driver's availability
				Driver driver = tripBooking.getDriver();
		}
	}
}
