package Reservation;

import Customer.Customer;
import car.Car;
import car.CarStatus;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationManager {
    private final Map<String, Reservation> allReservations = new ConcurrentHashMap<>();

    public Reservation createReservation(Customer customer, Car car, LocalDate startDate, LocalDate endDate) {
        System.out.println("🔍 Checking car availability for " + car.getCarName() + " (ID: " + car.getCarId() + ")");
        System.out.println("   📅 Requested period: " + startDate + " to " + endDate);
        
        if (!isCarAvailableForPeriod(car, startDate, endDate)) {
            System.out.println("❌ Car " + car.getCarName() + " is not available for the requested period");
            return null;
        }

        System.out.println("✅ Car " + car.getCarName() + " is available for booking");
        Reservation newReservation = new Reservation(customer, car, startDate, endDate);
        allReservations.put(newReservation.getReservationId(), newReservation);
        
        // Update car status to booked
        car.setCarStatus(CarStatus.Booked);
        System.out.println("🔒 Car " + car.getCarName() + " status updated to BOOKED");
        
        System.out.println("✅ Reservation created successfully with ID: " + newReservation.getReservationId());
        return newReservation;
    }

    public boolean isCarAvailableForPeriod(Car car, LocalDate requestedStartDate, LocalDate requestedEndDate) {
        System.out.println("🔍 Checking availability for car: " + car.getCarName() + " (ID: " + car.getCarId() + ")");
        
        for (Reservation existingReservation : allReservations.values()) {
            // Check if this reservation is for the same car and is not cancelled
            if (existingReservation.getCar().getCarId().equals(car.getCarId()) && 
                existingReservation.getReservationStatus() != ReservationStatus.CANCELLED) {
                
                LocalDate existingStartDate = existingReservation.getStartDate();
                LocalDate existingEndDate = existingReservation.getEndDate();
                
                System.out.println("   ⚠️ Found existing reservation: " + existingReservation.getReservationId());
                System.out.println("      📅 Existing period: " + existingStartDate + " to " + existingEndDate);
                
                // Check for date overlap
                boolean hasOverlap = !(requestedEndDate.isBefore(existingStartDate) || requestedStartDate.isAfter(existingEndDate));
                
                if (hasOverlap) {
                    System.out.println("   ❌ Date overlap detected - car not available");
                    return false;
                } else {
                    System.out.println("   ✅ No overlap with existing reservation");
                }
            }
        }
        
        System.out.println("✅ Car is available for the requested period");
        return true;
    }

    public boolean modifyReservation(String reservationId, LocalDate newStartDate, LocalDate newEndDate) {
        System.out.println("📝 Attempting to modify reservation: " + reservationId);
        System.out.println("   📅 New period: " + newStartDate + " to " + newEndDate);
        
        Reservation reservationToModify = allReservations.get(reservationId);
        if (reservationToModify == null) {
            System.out.println("❌ Reservation not found with ID: " + reservationId);
            return false;
        }
        
        System.out.println("✅ Found reservation to modify:");
        System.out.println("   👤 Customer: " + reservationToModify.getCustomer().getCustomerName());
        System.out.println("   🚗 Car: " + reservationToModify.getCar().getCarName());
        
        // Update the reservation dates
        reservationToModify.setStartDate(newStartDate);
        reservationToModify.setEndDate(newEndDate);
        
        System.out.println("✅ Reservation modified successfully");
        return true;
    }

    public void cancelReservation(String reservationId) {
        System.out.println("❌ Attempting to cancel reservation: " + reservationId);
        
        Reservation reservationToCancel = allReservations.remove(reservationId);
        if (reservationToCancel != null) {
            System.out.println("✅ Found reservation to cancel:");
            System.out.println("   👤 Customer: " + reservationToCancel.getCustomer().getCustomerName());
            System.out.println("   🚗 Car: " + reservationToCancel.getCar().getCarName());
            
            // Update car status back to available
            reservationToCancel.getCar().setCarStatus(CarStatus.Avaiable);
            System.out.println("🔄 Car " + reservationToCancel.getCar().getCarName() + " status updated to AVAILABLE");
            
            System.out.println("✅ Reservation cancelled successfully");
        } else {
            System.out.println("❌ Reservation not found with ID: " + reservationId);
        }
    }

    public double getTotalPrice(String reservationId) {
        System.out.println("💰 Calculating total price for reservation: " + reservationId);
        
        Reservation reservation = allReservations.get(reservationId);
        if (reservation == null) {
            System.out.println("❌ Reservation not found with ID: " + reservationId);
            return 0.0;
        }
        
        double totalCost = reservation.getTotalCost();
        System.out.println("✅ Total price calculated: ₹" + totalCost);
        return totalCost;
    }

    public Collection<Reservation> getAllReservations() {
        System.out.println("📊 Retrieving all reservations. Total count: " + allReservations.size());
        return allReservations.values();
    }
}
