package design_pattern.creational.abstract_factory;

public class Circle extends Shape {
	int r;
	
	public Circle(int r) {
		super();
		this.r = r;
	}

	@Override
	public float getArea() {
		return (float) (Math.PI*r*r);
	}

}
