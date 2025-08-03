package design_pattern.structural.adapter.example2;

/*
 * (Giải thích cái hình vẽ)
 * - Client giao tiếp với inteface Target nhưng cần dùng tới chức năng 
 *   ở Adaptee.
 * - Target sẽ khai báo chức năng của Adaptee và dùng implement 
 *   của nó là Adapter để cài đặt lại chức năng đó.
 * - Adapter sẽ giao tiếp với Adaptee để dùng lại 1 phần hoặc toàn 
 *   bộ chức năng của Adaptee để phục vụ client.
 *   
 * Ví dụ:
 * Mình có 1 class CheckNumberAdaptee chuyên thực hiện việc kiểm 
 * tra 1 chuỗi ký tự có phải là kiểu số không.
 * Client bây giờ cần chức năng kiểm tra 1 chuỗi ký tự có phải là 
 * số điện không.
 * Rõ ràng client không thể dùng trực tiếp được CheckNumberAdaptee, 
 * ta sẽ dùng thêm class Adapter để thực hiện chức năng kiểm tra số 
 * điện thoại bằng cách sử dụng chức năng của CheckNumberAdaptee 
 * kiểm tra chuỗi ký tự đó có phải là kiểu số không, nếu đúng thì 
 * kiểm tra thêm điều kiện của số điện thoại.
 */
public class Test {
	public static void main(String[] args) {
		PhoneTarget galaxyS8 = new PhoneAdapter();
		String input1 = "sdflkqweqweq";
		String input2 = "123";
		String input3 = "01234342342";

		System.out.println(input1 + " is phone number: " + galaxyS8.checkPhoneNumber(input1));
		System.out.println(input2 + " is phone number: " + galaxyS8.checkPhoneNumber(input2));
		System.out.println(input3 + " is phone number: " + galaxyS8.checkPhoneNumber(input3));
	}
}
