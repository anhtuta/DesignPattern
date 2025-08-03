package immutable;

/*
 * Immutable hiểu nôm na là không thể thay đổi còn mutable là có thể thay đổi.
 * 2 khái niệm Immutable và mutable thường được dùng class, object (Immutable đôi 
 * khi còn dùng với Collection nhưng mình sẽ không để cập ở bài này).
 * Ví dụ: Trong Java String là Immutable còn StringBuffer và StringBuilder là mutable.
 * 
 * 1 Class/Đối tượng được coi là immutable nếu các thuộc tính của nó 
 * không bao giờ bị thay đổi và chỉ có thể thiết lập lúc khởi tạo.
 * 
 * Tại sao lại dùng Immutable
	- Giống như ví dụ với String bên dưới, ta thấy được sử dụng immutable sẽ 
	tránh được sự thay đổi lẫn nhau khi đa tham chiếu (str1 và str2 cùng tham 
	chiếu tới 1 vùng nhớ nhưng khi str1 thay đổi thì str2 sẽ không thay đổi)
	- Thread Safe: Khi sử dụng immutable object ta sẽ không cần phải lo tới việc 
	nhiều thread cùng làm thay đổi giá trị của 1 object
	- Sử dụng các immutable object làm tham số của method sẽ không sợ nó bị thay
	đổi sau khi method kết thúc
	- Sử dụng immutable object để làm key trong HashMap hoặc đẩy vào HashTable 
	mà không gặp vấn đề gì khi lấy ra.
 */
public class StringDemo {
	public static void main(String[] args) {
		/****************** Immutable với String *****************/
		// Khởi tạo str1 = "first"
		String str1 = new String("first");
		
		// Khởi tạo str2 tham chiếu tới str1
		String str2 = str1;
		
		// String là immutable, bất kì thao tác nào trên String đều tạo ra 1 đối tượng mới
		// str1.concat("-second") sẽ trả về 1 đối tượng String mới có giá trị là
		// "first-second", sau đó nó sẽ gán vào biến str1. Do vậy str2 ko liên quan gì
		// và sẽ ko bị thay đổi
		str1 = str1.concat("-second");
		// Hoặc dùng: str1 += "-second";
		System.out.println("str1: " + str1);
		System.out.println("str2: " + str2);
		//str1: first-second
		//str2: first
		
		/****************** mutable với StringBuilder, StringBuffer *****************/
		// Khởi tạo builder1 = "first"
		StringBuffer builder1 = new StringBuffer("first");
		
		// Khởi tạo builder2 tham chiếu tới builder1
		StringBuffer builder2 = builder1;
		
		// StringBuffer là mutable, do đó khi append thì giá trị của nó sẽ thay đổi 
		// trên chính vùng nhớ ban dầu, mà vùng nhớ đó builder2 cũng trỏ tới, do đó
		// builder cũng bị thay đổi theo builder 1
		builder1.append("-second");
		System.out.println("builder1: "+builder1);
		System.out.println("builder2: "+builder2);
	}
}
