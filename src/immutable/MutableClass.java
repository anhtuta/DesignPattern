package immutable;

public class MutableClass {
	String message;
	int temperature;
	
	public MutableClass(String message, int temperature) {
		this.message = message;
		this.temperature = temperature;
	}
	
	public void showInfo() {
		System.out.println(message + " - " + temperature);
	}

	public static void main(String[] args) {
		MutableClass mc1 = new MutableClass("Today is 25-7-2018", 31);
		MutableClass mc2 = mc1;		//mc2 sẽ tham chiếu tới cùng ô nhớ mà mc1 tham chiếu tới
		
		mc1.message = mc1.message + " - updated!";
		mc1.temperature = mc1.temperature + 10;
		
		mc1.showInfo();
		mc2.showInfo();		//mc2 sẽ thay đổi theo mc1
	}
}
