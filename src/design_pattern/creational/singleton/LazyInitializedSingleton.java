package design_pattern.creational.singleton;

/*
 * Tạo thể hiện của Class Singleton trong method access.
 * 
 * Cách này đã khắc phục được nhược điểm của cách Eager initialization,
 * chỉ khi nào getInstance() được gọi thì instance mới được khởi tạo
 * 
 * Cách này có nhược điểm là không hoạt động đúng trong 
 * trường hợp có nhiều thread, giả sử có nhiều thread cùng 
 * lúc gọi method getInstance() sẽ có nhiều thể hiện khác 
 * nhau được tạo
 * 
 * Một nhược điểm nữa của Lazy Initialization cần quan tâm là: đối với
 * thao tác create instance quá chậm thì người dùng có phải chờ lâu
 * cho lần sử dụng đầu tiên.
 */
public class LazyInitializedSingleton {
	private static LazyInitializedSingleton instance;

	private LazyInitializedSingleton() {
	}

	public static LazyInitializedSingleton getInstance() {
		if (instance == null) {
			instance = new LazyInitializedSingleton();
		}
		return instance;
	}
}
