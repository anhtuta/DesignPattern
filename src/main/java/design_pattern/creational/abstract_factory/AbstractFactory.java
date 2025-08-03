package design_pattern.creational.abstract_factory;

public abstract class AbstractFactory {
	abstract Shape getShape(String shape);
	abstract Vehicle getVehicle(String vehicle);
}
