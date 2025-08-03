package design_pattern.creational.factory.gpcoder;

/**
 * chỉ cần gọi duy nhất một phương thức BankFactory.getBank() là
 * có thể sử dụng được dịch vụ của một ngân hàng bất kỳ
 * 
 * Khi hệ thống muốn cung cấp thêm dịch vụ của một ngân hàng khác,
 * chẳng hạn VietinBank, thì cần tạo thêm một class mới implement
 * từ interface Bank, và thêm vào logic khởi tạo Bank trong Factory
 * là xong. Nó không làm ảnh hưởng đến code ở phía class Test
 */
public class Test {
	public static void main(String[] args) {
		Bank bank = BankFactory.getBank(BankType.TPBANK);
		System.out.println(bank.getBankName()); // TPBank
	}
}
