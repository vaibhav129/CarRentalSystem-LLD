# Car Rental System - LLD Project

## Overview
This is a Java-based Car Rental System designed for Low-Level Design (LLD) interviews. It demonstrates core OOP principles, modular architecture, and basic concurrency handling for car bookings.

## Class Diagram
The following class diagram describes the core structure. The diagram is written in PlantUML format (`docs/class_diagram.puml`). You can render it using [PlantUML Online](https://www.plantuml.com/plantuml/) or any compatible tool.

```
@startuml
!include docs/class_diagram.puml
@enduml
```

## Architecture & Packages

- **car/**: Car models, car factory, car types, car status
- **Customer/**: Customer entity
- **Reservation/**: Reservation entity, manager, reservation status
- **Payment/**: Payment interface and implementations
- **CarRentalSystem/**: Core system logic (Singleton)
- **Main.java**: Entry point and concurrent booking test

## Class Explanations

### car/
- **Car**: Base class for all cars. Contains car ID, name, license plate, type, and status.
- **LuxuryCar / NormalCar**: Specialized car types extending Car.
- **CarFactory**: Factory for creating car instances.
- **CarType**: Enum for car categories (LUXURY, NORMAL).
- **CarStatus**: Enum for car status (Available, Booked).

### Customer/
- **Customer**: Represents a customer with name, email, and customer ID.

### Reservation/
- **Reservation**: Represents a booking, including customer, car, period, cost, and status.
- **ReservationManager**: Handles creation, modification, and cancellation of reservations. Ensures no double booking.
- **ReservationStatus**: Enum for reservation state (ACTIVE, CANCELLED).

### Payment/
- **Payment (interface)**: Contract for processing payments.
- **CashPayment / CreditPayment**: Implementations of payment methods.

### CarRentalSystem/
- **CarRentalSystem**: Singleton class managing cars, reservations, and payments. Entry point for all operations.

### Main.java
- **Main**: Demo class with a concurrent booking scenario to test thread safety.

## How to Run
1. Compile all Java files in `src/`.
2. Run `Main.java`.
3. You will see logs for car creation, reservation attempts, and final reservation summary.

## How to Render the Class Diagram
- Open `docs/class_diagram.puml` in [PlantUML Online](https://www.plantuml.com/plantuml/).
- Or use any PlantUML-compatible tool to generate a PNG/SVG.

## GitHub Repository
This project is to be pushed to: [https://github.com/vaibhav129/CarRentalSystem-LLD.git](https://github.com/vaibhav129/CarRentalSystem-LLD.git)

## Author
- Prepared for LLD interview practice
- Mentor: Cascade AI
