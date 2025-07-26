package car;

public class LuxuryCar extends Car {
    public LuxuryCar(String carId, String carName, CarType carType, CarStatus carStatus, String licensePlate, double basePrice) {
        super(carId, carName, carType, carStatus, licensePlate, basePrice);
        System.out.println("✨ Luxury car constructor called for: " + carName);
    }

    @Override
    public double setprice(int numberOfDays) {
        double basePrice = getBasePrice();
        double luxuryMultiplier = 2.0;
        double totalPrice = basePrice * numberOfDays * luxuryMultiplier;
        
        System.out.println("💰 Luxury Car Pricing Calculation:");
        System.out.println("   💵 Base Price: ₹" + basePrice);
        System.out.println("   📅 Number of Days: " + numberOfDays);
        System.out.println("   ✨ Luxury Multiplier: " + luxuryMultiplier + "x");
        System.out.println("   💰 Total Price: ₹" + basePrice + " × " + numberOfDays + " × " + luxuryMultiplier + " = ₹" + totalPrice);
        
        return totalPrice;
    }
}
