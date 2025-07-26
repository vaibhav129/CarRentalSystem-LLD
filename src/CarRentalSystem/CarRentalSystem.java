package CarRentalSystem;

import Payment.*;
import Reservation.*;
import car.Car;
import car.CarFactory;
import car.CarStatus;
import car.CarType;
import Customer.Customer;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalSystem {
    private static CarRentalSystem singletonInstance;

    private final Map<String, Car> availableCars;
    private final ReservationManager reservationManager;
    private final CarFactory carFactory;
    private final Payment paymentProcessor;

    private CarRentalSystem() {
        System.out.println("🏢 Initializing Car Rental System...");
        this.paymentProcessor = new CreditPayment(); // You can plug in CashPayment, etc.
        this.availableCars = new ConcurrentHashMap<>();
        this.carFactory = new CarFactory();
        this.reservationManager = new ReservationManager();
        System.out.println("✅ Car Rental System initialized successfully");
    }

    public static synchronized CarRentalSystem getInstance() {
        if (singletonInstance == null) {
            System.out.println("🔧 Creating new Car Rental System instance (Singleton pattern)");
            singletonInstance = new CarRentalSystem();
        }
        return singletonInstance;
    }

    // ---- Car Operations ----
    public void createCar(Car car) {
        System.out.println("🚗 Adding car to system: " + car.getCarName() + " (License: " + car.getLicensePlate() + ")");
        availableCars.put(car.getLicensePlate(), car);
        System.out.println("✅ Car added successfully. Total cars in system: " + availableCars.size());
    }

    public void removeCar(String licensePlate) {
        System.out.println("🗑️ Removing car from system with license: " + licensePlate);
        Car removedCar = availableCars.remove(licensePlate);
        if (removedCar != null) {
            System.out.println("✅ Car " + removedCar.getCarName() + " removed successfully");
        } else {
            System.out.println("❌ Car with license " + licensePlate + " not found in system");
        }
    }

    public List<Car> searchCar(String carName, CarType carType) {
        System.out.println("🔍 Searching for cars with name: " + carName + " and type: " + carType);
        List<Car> searchResults = new ArrayList<>();
        
        for (Car currentCar : availableCars.values()) {
            if (currentCar.getCarName().equals(carName) &&
                    currentCar.getCarType() == carType &&
                    currentCar.getCarStatus() == CarStatus.Avaiable) {
                searchResults.add(currentCar);
                System.out.println("   ✅ Found matching car: " + currentCar.getCarName() + " (ID: " + currentCar.getCarId() + ")");
            }
        }
        
        System.out.println("📊 Search completed. Found " + searchResults.size() + " matching cars");
        return searchResults;
    }

    public Collection<Car> getAllCars() {
        System.out.println("📊 Retrieving all cars from system. Total count: " + availableCars.size());
        return availableCars.values();
    }

    // ---- Reservation Operations ----
    public synchronized Reservation createReservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        System.out.println("📋 Creating new reservation request:");
        System.out.println("   👤 Customer: " + customer.getCustomerName());
        System.out.println("   🚗 Car: " + car.getCarName());
        System.out.println("   📅 Period: " + startDate + " to " + endDate);
        
        Reservation newReservation = reservationManager.createReservation(customer, car, startDate, endDate);
        
        if (newReservation != null) {
            System.out.println("✅ Reservation created successfully through CarRentalSystem");
        } else {
            System.out.println("❌ Reservation creation failed through CarRentalSystem");
        }
        
        return newReservation;
    }

    public boolean modifyReservation(String reservationId, LocalDate newStartDate, LocalDate newEndDate) {
        System.out.println("📝 CarRentalSystem: Modifying reservation " + reservationId);
        boolean modificationSuccess = reservationManager.modifyReservation(reservationId, newStartDate, newEndDate);
        
        if (modificationSuccess) {
            System.out.println("✅ Reservation modification completed successfully");
        } else {
            System.out.println("❌ Reservation modification failed");
        }
        
        return modificationSuccess;
    }

    public synchronized void cancelReservation(String reservationId) {
        System.out.println("❌ CarRentalSystem: Cancelling reservation " + reservationId);
        reservationManager.cancelReservation(reservationId);
        System.out.println("✅ Reservation cancellation completed");
    }

    public double getTotalPrice(String reservationId) {
        System.out.println("💰 CarRentalSystem: Getting total price for reservation " + reservationId);
        double totalPrice = reservationManager.getTotalPrice(reservationId);
        System.out.println("✅ Total price retrieved: ₹" + totalPrice);
        return totalPrice;
    }

    public Collection<Reservation> getAllReservations() {
        System.out.println("📊 CarRentalSystem: Retrieving all reservations");
        return reservationManager.getAllReservations();
    }

    // ---- Payment ----
    public boolean processPayment() {
        System.out.println("💳 Processing payment through CarRentalSystem...");
        boolean paymentSuccess = paymentProcessor.processPayment();
        
        if (paymentSuccess) {
            System.out.println("✅ Payment processed successfully");
        } else {
            System.out.println("❌ Payment processing failed");
        }
        
        return paymentSuccess;
    }
}
