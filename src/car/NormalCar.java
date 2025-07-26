package car;

public class NormalCar extends Car {

    public NormalCar(String carId, String carName, CarType carType, CarStatus carStatus, String licensePlate, double basePrice) {
        super(carId, carName, carType, carStatus, licensePlate, basePrice);
        System.out.println("🚙 Normal car constructor called for: " + carName);
    }

    @Override
    public double setprice(int numberOfDays) {
        double basePrice = getBasePrice();
        double normalMultiplier = 3.0;
        double totalPrice = basePrice * numberOfDays * normalMultiplier;
        
        System.out.println("💰 Normal Car Pricing Calculation:");
        System.out.println("   💵 Base Price: ₹" + basePrice);
        System.out.println("   📅 Number of Days: " + numberOfDays);
        System.out.println("   🚙 Normal Multiplier: " + normalMultiplier + "x");
        System.out.println("   💰 Total Price: ₹" + basePrice + " × " + numberOfDays + " × " + normalMultiplier + " = ₹" + totalPrice);
        
        return totalPrice;
    }
}
