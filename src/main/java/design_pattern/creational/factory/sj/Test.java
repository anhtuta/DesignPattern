package design_pattern.creational.factory.sj;

public class Test {

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		
		Shape s1 = shapeFactory.getShape(ShapeEnum.CICLE);
		Shape s2 = shapeFactory.getShape(ShapeEnum.SQUARE);
		
		System.out.println("Area of s1 = " + s1.getArea());
		System.out.println("Area of s2 = " + s2.getArea());
	}

}
