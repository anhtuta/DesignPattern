package design_pattern.creational.abstract_factory;

public class VehicleFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shape) {
		return null;
	}

	@Override
	Vehicle getVehicle(String vehicle) {
		if(vehicle == null) return null;
		
		if(vehicle.equalsIgnoreCase("bicycle")) {
			return new Bicycle();
		}
		
		if(vehicle.equalsIgnoreCase("car")) {
			return new Car();
		}
		
		return null;
	}
}
