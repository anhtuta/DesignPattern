package design_pattern.creational.abstract_factory;

public class Test {

	public static void main(String[] args) {
		AbstractFactory shapeFactory = FactoryProducer.getFactory("shape");
		Shape s1 = shapeFactory.getShape("square");
		System.out.println(s1.getArea());
		
		AbstractFactory vehicleFactory = FactoryProducer.getFactory("vehicle");
		Vehicle v1 = vehicleFactory.getVehicle("car");
		System.out.println(v1.getVelocity());
	}

}
