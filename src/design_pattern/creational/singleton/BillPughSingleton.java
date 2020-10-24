package design_pattern.creational.singleton;

/*
 * Tạo thể hiện của class Singleton bằng static inner class.
 * Khi singleton class được tải, class SingletonHeplper vẫn 
 * không được tải vào bộ nhớ mà chỉ khi method getInstance() 
 * được gọi, class này (SingletonHeplper) mới được tải và tạo thể hiện
 * cho Singleton class
 * Cách này cũng không yêu cầu synchronization
 * 
 * Cách làm này được đánh giá là cách triển khai Singleton nhanh
 * và hiệu quả nhất
 */
public class BillPughSingleton {
	private BillPughSingleton() {
		System.out.println("Constructor of BillPughSingleton");
	}

	private static class SingletonHelper {
		private static final BillPughSingleton INSTANCE = new BillPughSingleton();
	}

	public static BillPughSingleton getInstance() {
		return SingletonHelper.INSTANCE;
	}
}
