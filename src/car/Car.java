package car;

public abstract class Car {
    private String carName;
    private CarType carType;
    private String carId;
    private String licensePlate;
    private double basePrice;
    private CarStatus carStatus;

    public Car(String carId, String carName, CarType carType, CarStatus carStatus, String licensePlate, double basePrice) {
        this.carId = carId;
        this.carName = carName;
        this.carType = carType;
        this.licensePlate = licensePlate;
        this.carStatus = CarStatus.Avaiable; // Always start as available
        this.basePrice = basePrice;
        
        System.out.println("Car created: " + carName + " (ID: " + carId + ", License: " + licensePlate + ")");
        System.out.println("   Base Price: Rs." + basePrice + " | Type: " + carType + " | Status: " + this.carStatus);
    }

    public abstract double setprice(int numberOfDays);

    // Getter methods with meaningful names
    public String getLicensePlate() {
        return licensePlate;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getCarId() {
        return carId;
    }

    public String getCarName() {
        return carName;
    }

    public CarType getCarType() {
        return carType;
    }

    public CarStatus getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(CarStatus newCarStatus) {
        System.out.println("Car " + carName + " (ID: " + carId + ") status changed from " + carStatus + " to " + newCarStatus);
        this.carStatus = newCarStatus;
    }
    
    // Legacy getter methods for backward compatibility
    public String getLc() {
        return getLicensePlate();
    }

    public double getBprice() {
        return getBasePrice();
    }

    public String getCarname() {
        return getCarName();
    }

    public CarType getCartype() {
        return getCarType();
    }

    public CarStatus getCs() {
        return getCarStatus();
    }

    public void setCs(CarStatus carStatus) {
        setCarStatus(carStatus);
    }
}
