package Reservation;

import java.time.LocalDate;
import java.util.UUID;

import Customer.Customer;
import car.Car;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private int numberOfRentalDays;
    private ReservationStatus reservationStatus;

    public Reservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        this.reservationId = UUID.randomUUID().toString(); // Unique reservation ID
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfRentalDays = calculateNumberOfDays(startDate, endDate);
        this.reservationStatus = ReservationStatus.BOOKED;
        
        System.out.println("📋 New reservation created:");
        System.out.println("   🆔 Reservation ID: " + reservationId);
        System.out.println("   👤 Customer: " + customer.getCustomerName());
        System.out.println("   🚗 Car: " + car.getCarName() + " (ID: " + car.getCarId() + ")");
        System.out.println("   📅 Period: " + startDate + " to " + endDate + " (" + numberOfRentalDays + " days)");
        System.out.println("   💰 Total Cost: ₹" + getTotalCost());
        System.out.println("   📊 Status: " + reservationStatus);
    }

    private int calculateNumberOfDays(LocalDate startDate, LocalDate endDate) {
        int days = (int) (endDate.toEpochDay() - startDate.toEpochDay());
        System.out.println("📅 Calculating rental period: " + startDate + " to " + endDate + " = " + days + " days");
        return days;
    }

    public double getTotalCost() {
        double totalCost = car.setprice(numberOfRentalDays);
        System.out.println("💰 Calculating total cost for " + numberOfRentalDays + " days: ₹" + totalCost);
        return totalCost;
    }

    public void cancel() {
        System.out.println("❌ Cancelling reservation " + reservationId + "...");
        this.reservationStatus = ReservationStatus.CANCELLED;
        System.out.println("✅ Reservation " + reservationId + " has been cancelled successfully.");
    }

    // Getter methods with meaningful names
    public String getReservationId() {
        return reservationId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getNumberOfRentalDays() {
        return numberOfRentalDays;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    // Setter methods
    public void setStartDate(LocalDate newStartDate) {
        System.out.println("📅 Updating reservation " + reservationId + " start date from " + startDate + " to " + newStartDate);
        this.startDate = newStartDate;
        this.numberOfRentalDays = calculateNumberOfDays(newStartDate, endDate);
    }

    public void setEndDate(LocalDate newEndDate) {
        System.out.println("📅 Updating reservation " + reservationId + " end date from " + endDate + " to " + newEndDate);
        this.endDate = newEndDate;
        this.numberOfRentalDays = calculateNumberOfDays(startDate, newEndDate);
    }

    // Legacy getter methods for backward compatibility
    public String getId() {
        return getReservationId();
    }

    public int getRentalDays() {
        return getNumberOfRentalDays();
    }

    public ReservationStatus getStatus() {
        return getReservationStatus();
    }
}
