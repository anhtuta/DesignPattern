package dp_refactoring_guru.builder.car_manufacturing;

import dp_refactoring_guru.builder.car_manufacturing.components.Engine;
import dp_refactoring_guru.builder.car_manufacturing.components.GPSNavigator;
import dp_refactoring_guru.builder.car_manufacturing.components.Transmission;
import dp_refactoring_guru.builder.car_manufacturing.components.TripComputer;

enum CarType {
    CITY_CAR, SPORTS_CAR, SUV
}

/**
 * EN: Car is a product class.
 */
public class Car {
    private final CarType carType;
    private final int seats;
    private final Engine engine;
    private final Transmission transmission;
    private final TripComputer tripComputer;
    private final GPSNavigator gpsNavigator;
    private double fuel = 0;

    public Car(CarType carType, int seats, Engine engine, Transmission transmission,
            TripComputer tripComputer, GPSNavigator gpsNavigator) {
        this.carType = carType;
        this.seats = seats;
        this.engine = engine;
        this.transmission = transmission;
        this.tripComputer = tripComputer;
        if (this.tripComputer != null) {
            this.tripComputer.setCar(this);
        }
        this.gpsNavigator = gpsNavigator;
    }

    public CarType getCarType() {
        return carType;
    }

    public int getSeats() {
        return seats;
    }

    public Engine getEngine() {
        return engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public TripComputer getTripComputer() {
        return tripComputer;
    }

    public GPSNavigator getGpsNavigator() {
        return gpsNavigator;
    }

    public double getFuel() {
        return fuel;
    }

    public String print() {
        String info = "";
        info += "Type of car: " + carType + "\n";
        info += "Count of seats: " + seats + "\n";
        info += "Engine: volume - " + engine.getVolume() + "; mileage - " + engine.getMileage()
                + "\n";
        info += "Transmission: " + transmission + "\n";
        if (this.tripComputer != null) {
            info += "Trip Computer: Functional" + "\n";
        } else {
            info += "Trip Computer: N/A" + "\n";
        }
        if (this.gpsNavigator != null) {
            info += "GPS Navigator: Functional" + "\n";
        } else {
            info += "GPS Navigator: N/A" + "\n";
        }
        return info;
    }

    static class CarBuilder {
        private CarType type;
        private int seats;
        private Engine engine;
        private Transmission transmission;
        private TripComputer tripComputer;
        private GPSNavigator gpsNavigator;

        public void setCarType(CarType type) {
            this.type = type;
        }

        public void setSeats(int seats) {
            this.seats = seats;
        }

        public void setEngine(Engine engine) {
            this.engine = engine;
        }

        public void setTransmission(Transmission transmission) {
            this.transmission = transmission;
        }

        public void setTripComputer(TripComputer tripComputer) {
            this.tripComputer = tripComputer;
        }

        public void setGPSNavigator(GPSNavigator gpsNavigator) {
            this.gpsNavigator = gpsNavigator;
        }

        public Car getResult() {
            return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
        }
    }
}

