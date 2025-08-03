package design_pattern.creational.factory.sj;

public class Square extends Shape {
	int a;

	public Square(int a) {
		super();
		this.a = a;
	}

	@Override
	public float getArea() {
		return a*a;
	}
}
