package design_pattern.creational.singleton;

/*
 * Thể hiện Singleton Class được tạo lúc class được tải, đây là cách 
 * dễ dàng nhất để tạo 1 singleton class nhưng nó có nhược điểm là thể 
 * hiện được tạo kể cả khi nó không được dùng đến, và không thể bắt được 
 * ngoại lệ lúc tạo thể hiện
 */
public class EagerInitializedSingleton {
	//Biến private static của class, nó là thể hiện duy nhất của class
	private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

	//Hàm khởi tạo private để ngăn cản việc tạo thể hiện của class từ các class khác
	private EagerInitializedSingleton() {}
	
	//Method public static để trả về thể hiện của class.
	public static EagerInitializedSingleton getInstance() {
		return instance;
	}
}
