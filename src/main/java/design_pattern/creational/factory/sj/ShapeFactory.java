package design_pattern.creational.factory.sj;

public class ShapeFactory {
	public Shape getShape(ShapeEnum se) {
		if(se == null) return null;
		
		if(se.equals(ShapeEnum.CICLE)) {
			return new Circle(10);
		}
		
		if(se.equals(ShapeEnum.SQUARE)) {
			return new Square(10);
		}
		
		if(se.equals(ShapeEnum.TRIANGLE)) {
			return new Triangle(10, 10, 10);
		}
		
		return null;
	}
}
