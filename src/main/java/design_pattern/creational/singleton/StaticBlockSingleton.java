package design_pattern.creational.singleton;

/*
 * Khởi tạo bằng static block, nó giống hệt EagerInitializedSingleton
 * tuy nhiên có thể sử lý được ngoại lệ khi khởi tạo thể hiện đầu tiên.
 */
public class StaticBlockSingleton {
	private static final StaticBlockSingleton instance;

	private StaticBlockSingleton() {}

	// Nói qua về static block
	// Trong Java: static block dùng để khởi tạo các biến static của 1 class
	// This code inside static block is executed only once: the first time
	// you make an object of that class or the first time you access a static member
	// of that class (even if you never make an object of that class)

	// static block initialization for exception handling
	static {
		try {
			instance = new StaticBlockSingleton();
		} catch (Exception e) {
			throw new RuntimeException("Exception occured in creating singleton instance");
		}
	}

	public static StaticBlockSingleton getInstance() {
		return instance;
	}
}
