package design_pattern.creational.abstract_factory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {

		if (choice.equalsIgnoreCase("shape")) {
			return new ShapeFactory();

		} else if (choice.equalsIgnoreCase("vehicle")) {
			return new VehicleFactory();
		}

		return null;
	}
}
