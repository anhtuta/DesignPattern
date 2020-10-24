package design_pattern.creational.factory.sj;

public class Triangle extends Shape {
	public int a,b,c;

	public Triangle(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public float getArea() {
		int p = (a+b+c)/2;
		return (float) Math.sqrt(p*(p-a)*(p-b)*(p-c));
	}

}
