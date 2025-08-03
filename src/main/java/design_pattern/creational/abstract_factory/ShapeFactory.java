package design_pattern.creational.abstract_factory;

public class ShapeFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shape) {
		if(shape == null) return null;
		
		if(shape.equalsIgnoreCase("circle")) {
			return new Circle(10);
		}
		
		if(shape.equalsIgnoreCase("square")) {
			return new Square(10);
		}
		
		if(shape.equalsIgnoreCase("triangle")) {
			return new Triangle(10, 10, 10);
		}
		
		return null;
	}

	@Override
	Vehicle getVehicle(String vehicle) {
		return null;
	}
}
