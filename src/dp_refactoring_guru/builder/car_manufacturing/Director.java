package dp_refactoring_guru.builder.car_manufacturing;

import dp_refactoring_guru.builder.car_manufacturing.Car.CarBuilder;
import dp_refactoring_guru.builder.car_manufacturing.components.Engine;
import dp_refactoring_guru.builder.car_manufacturing.components.GPSNavigator;
import dp_refactoring_guru.builder.car_manufacturing.components.Transmission;
import dp_refactoring_guru.builder.car_manufacturing.components.TripComputer;

/**
 * EN: Director defines the order of building steps. It works with a builder
 * object through common Builder interface. Therefore it may not know what
 * product is being built.
 */
public class Director {

    public Car constructSportsCar() {
        CarBuilder builder = new CarBuilder();
        builder.setCarType(CarType.SPORTS_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(3.0, 0));
        builder.setTransmission(Transmission.SEMI_AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
        return builder.getResult();
    }

    public Car constructCityCar() {
        CarBuilder builder = new CarBuilder();
        builder.setCarType(CarType.CITY_CAR);
        builder.setSeats(2);
        builder.setEngine(new Engine(1.2, 0));
        builder.setTransmission(Transmission.AUTOMATIC);
        builder.setTripComputer(new TripComputer());
        builder.setGPSNavigator(new GPSNavigator());
        return builder.getResult();
    }

    public Car constructSUV() {
        CarBuilder builder = new CarBuilder();
        builder.setCarType(CarType.SUV);
        builder.setSeats(4);
        builder.setEngine(new Engine(2.5, 0));
        builder.setTransmission(Transmission.MANUAL);
        builder.setGPSNavigator(new GPSNavigator());
        return builder.getResult();
    }
}
