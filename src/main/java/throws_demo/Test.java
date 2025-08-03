package throws_demo;

public class Test {
	private final static int a = test();
	
	private static int test() {	//ko cần thêm throws ExceptionInInitializerError
		try {
			int a = 10/0;
			System.out.println("a = " + a);
			return a;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
			//throw new Error(e);
		}
	}
	
	public static int getA() {
		return a;
	}
	
	public static void main(String[] args) {
		Test.getA();
	}
}
