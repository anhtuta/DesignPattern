package immutable;

/*
 * Cách tạo 1 Class Immutable
	- Phải là final class (không thể thừa kế bởi class khác)
	- Các field phải là private final
	- Không có các method làm thay đổi trạng thái của các field 
	  (Ví dụ: chỉ có hàm get, không có các hàm set)
	- Nếu có field nào là Object thì field đó cũng phải là 1 
	  immutable Object hoặc khi khởi tạo/lấy ra field đó ta phải 
	  clone ra 1 bản khác.
 */
public final class ImmutableClass {
	// Chú ý: biến final thì phải đc khởi tạo, nhưng do có hàm khởi tạo rồi nên ko cần
	// khởi tạo ở đây. Nếu hàm khởi tạo mà thiếu 1 trong các final field này thì sẽ bị lỗi
	private final String message;
	private final int temperature;
	
//	public ImmutableClass() {
//		Hàm này sẽ lỗi vì class này có các final field
//	}
	
	public ImmutableClass(String message, int temperature) {
		this.message = message;
		this.temperature = temperature;
	}
	
	public void showInfo() {
		System.out.println(message + " - " + temperature);
	}

	public static void main(String[] args) {
		ImmutableClass ic1 = new ImmutableClass("Today is 25-7-2018", 31);
		ImmutableClass ic2 = ic1;
		
		//không thể thay đổi giá trị cho các field trong class, do đó ko sợ
		//ic2 thay đổi theo ic1
//		ic1.message = ic1.message + " - updated!";
//		ic1.temperature = ic1.temperature + 10;
		
		ic2.showInfo();
		ic2.showInfo();
	}
}
