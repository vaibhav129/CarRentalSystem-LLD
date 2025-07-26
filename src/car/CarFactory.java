package car;

public class CarFactory {
    public Car createcar(CarType carType, CarStatus carStatus, String carName, String carId, String licensePlate, double basePrice) {
        System.out.println("🏭 Car Factory: Creating new car...");
        System.out.println("   🚗 Type: " + carType);
        System.out.println("   📝 Name: " + carName);
        System.out.println("   🆔 ID: " + carId);
        System.out.println("   🏷️ License: " + licensePlate);
        System.out.println("   💰 Base Price: ₹" + basePrice);
        
        if (carType.equals(CarType.LUXURY)) {
            System.out.println("✨ Creating luxury car...");
            LuxuryCar luxuryCar = new LuxuryCar(carId, carName, carType, carStatus, licensePlate, basePrice);
            System.out.println("✅ Luxury car created successfully");
            return luxuryCar;
        } else if (carType.equals(CarType.NORMAL)) {
            System.out.println("🚙 Creating normal car...");
            NormalCar normalCar = new NormalCar(carId, carName, carType, carStatus, licensePlate, basePrice);
            System.out.println("✅ Normal car created successfully");
            return normalCar;
        } else {
            System.out.println("❌ ERROR: Car type '" + carType + "' is not available");
            System.out.println("   Available types: LUXURY, NORMAL");
        }
        return null;
    }
}
