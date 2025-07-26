import CarRentalSystem.CarRentalSystem;
import Customer.Customer;
import car.Car;
import car.CarFactory;
import car.CarStatus;
import car.CarType;
import Reservation.Reservation;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Car Rental System - Concurrent Booking Test ===");
        System.out.println("Starting the car rental system...\n");

        // Initialize the car rental system (Singleton pattern)
        CarRentalSystem carRentalSystem = CarRentalSystem.getInstance();
        System.out.println("Car rental system initialized successfully");

        // Create a car factory to manufacture cars
        CarFactory carFactory = new CarFactory();
        System.out.println("Car factory created");

        // Create a luxury car for testing
        Car luxuryCar = carFactory.createcar(CarType.LUXURY, CarStatus.Avaiable, "BMW X5", "CAR001", "PB01AB1234", 1000.0);
        carRentalSystem.createCar(luxuryCar);

        // Create two customers for concurrent booking test
        Customer firstCustomer = new Customer("John Smith", "john.smith@email.com", "CUST001");
        Customer secondCustomer = new Customer("Jane Doe", "jane.doe@email.com", "CUST002");

        // Set booking dates
        LocalDate bookingStartDate = LocalDate.now().plusDays(1);
        LocalDate bookingEndDate = bookingStartDate.plusDays(3);
        System.out.println("Booking period: " + bookingStartDate + " to " + bookingEndDate + " (" + 
                          (bookingEndDate.toEpochDay() - bookingStartDate.toEpochDay()) + " days)");

        System.out.println("\nStarting concurrent booking test...");
        System.out.println("Both customers will try to book the same car for overlapping dates");

        // Create first thread for first customer
        Thread firstCustomerThread = new Thread(() -> {
            System.out.println("Thread 1: " + firstCustomer.getName() + " attempting to book car...");
            Reservation firstCustomerReservation = carRentalSystem.createReservation(firstCustomer, luxuryCar, bookingStartDate, bookingEndDate);
            if (firstCustomerReservation != null) {
                System.out.println("SUCCESS: " + firstCustomer.getName() + " booked car successfully!");
                System.out.println("   Reservation ID: " + firstCustomerReservation.getId());
                System.out.println("   Total Cost: Rs." + carRentalSystem.getTotalPrice(firstCustomerReservation.getId()));
            } else {
                System.out.println("FAILED: " + firstCustomer.getName() + " could not book car - car not available");
            }
        });

        // Create second thread for second customer
        Thread secondCustomerThread = new Thread(() -> {
            System.out.println("Thread 2: " + secondCustomer.getName() + " attempting to book car...");
            Reservation secondCustomerReservation = carRentalSystem.createReservation(secondCustomer, luxuryCar, bookingStartDate, bookingEndDate);
            if (secondCustomerReservation != null) {
                System.out.println("SUCCESS: " + secondCustomer.getName() + " booked car successfully!");
                System.out.println("   Reservation ID: " + secondCustomerReservation.getId());
                System.out.println("   Total Cost: Rs." + carRentalSystem.getTotalPrice(secondCustomerReservation.getId()));
            } else {
                System.out.println("FAILED: " + secondCustomer.getName() + " could not book car - car not available");
            }
        });

        // Start both threads simultaneously
        firstCustomerThread.start();
        secondCustomerThread.start();

        // Wait for both threads to complete
        firstCustomerThread.join();
        secondCustomerThread.join();

        // Display final results
        System.out.println("\n=== FINAL RESERVATION SUMMARY ===");
        System.out.println("Total reservations in system: " + carRentalSystem.getAllReservations().size());
        
        for (Reservation currentReservation : carRentalSystem.getAllReservations()) {
            System.out.println("Reservation Details:");
            System.out.println("   Customer: " + currentReservation.getCustomer().getName());
            System.out.println("   Car: " + currentReservation.getCar().getCarName() + " (ID: " + currentReservation.getCar().getCarId() + ")");
            System.out.println("   Period: " + currentReservation.getStartDate() + " to " + currentReservation.getEndDate());
            System.out.println("   Cost: Rs." + currentReservation.getTotalCost());
            System.out.println("   Reservation ID: " + currentReservation.getId());
            System.out.println("   Status: " + currentReservation.getStatus());
        }

        System.out.println("\n=== TEST COMPLETED ===");
        System.out.println("Expected result: Only one customer should successfully book the car");
        System.out.println("due to concurrent access control and car availability checking.");
    }
}
