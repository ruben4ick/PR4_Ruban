package org.example;

public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", 2020);
        Car car = new Car("Honda", "Civic", 2018, 4);
        Truck truck = new Truck("Ford", "F-150", 2019, 1200.5);

        try {
            YearValidator.validate(vehicle);
            YearValidator.validate(car);
            YearValidator.validate(truck);
            System.out.println("All years are valid!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String carString = CarToString.toString(car);
        System.out.println(carString);

        String vehicleString = VehicleToString.toString(vehicle);
        System.out.println(vehicleString);

        String truckString = TruckToString.toString(truck);
        System.out.println(truckString);


    }
}
