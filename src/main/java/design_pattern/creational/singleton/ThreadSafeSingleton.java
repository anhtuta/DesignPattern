package design_pattern.creational.singleton;

/*
 * Giống LazyInitializedSingleton nhưng method getInstance() 
 * được đánh dấu là synchronized tức là chỉ có duy nhất 1 
 * thread được gọi đến nó trong 1 thời điểm
 * => đảm bảo rằng chỉ có duy nhất 1 thể hiện của class
 * 
 * Cách này có nhược điểm là một phương thức synchronized sẽ chạy
 * rất chậm và tốn hiệu năng, bất kỳ Thread nào gọi đến đều phải
 * chờ nếu có một Thread khác đang sử dụng
 */
public class ThreadSafeSingleton {
	private static ThreadSafeSingleton instance;

	private ThreadSafeSingleton() {
		System.out.println("Constructor of ThreadSafeSingleton");
	}

	public static synchronized ThreadSafeSingleton getInstance() {
		if (instance == null) {
			instance = new ThreadSafeSingleton();
		}
		return instance;
	}
}
