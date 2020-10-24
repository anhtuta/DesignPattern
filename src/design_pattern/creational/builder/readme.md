# Builder Pattern là gì
- Builder Pattern là một mẫu thiết kế tạo dựng (Creation Pattern). 
- Builder được xây dựng để khắc phục một số nhược điểm của 
  Factory và Abstract Factory: khi mà Object có nhiều thuộc tính.
- Bản chất của Builder Pattern tức là Xây một cái mới từ những cái có sẵn

# Vấn đề
Có ba vấn đề chính với Factory Pattern và Abstract Factory Pattern 
khi Object có nhiều thuộc tính:
- Quá nhiều tham số phải truyền vào từ phía client tới Factory Class
- Một số tham số có thể là tùy chọn nhưng trong Factory Pattern, chúng ta 
  phải gửi tất cả tham số, với tham số tùy chọn nếu ko nhập gì thì sẽ truyền là null.
- Nếu một Object có quá nhiều thuộc tính thì việc tạo sẽ phức tạp.

Chúng ta có thể sử lý những vấn đề này với một số lượng lớn các tham số bằng
việc cung cấp một hàm khởi tạo với những tham số bắt buộc và các method
getter/setter để cài đặt các tham số tùy chọn. Vấn đề với hướng tiếp cận
này là trạng thái của Object sẽ không nhất quán cho tới khi tất cả các
thuộc tính được cài đặt một cách rõ ràng

# Giải quyết
Builder pattern xử lý các vấn đề này bằng việc cung cấp một cách 
xây dựng đối tượng từng bước một và cung cấp một method để trả 
và đối tượng cuối cùng.

# Ví dụ
- java.lang.StringBuilder#append() (unsynchronized)
- java.lang.StringBuffer#append() (synchronized)
- Ví dụ khi xây dựng ứng dụng quản lý nhà hàng. Ở thời điểm hiện tại 
  người ta mới nghĩ ra chừng ấy thực đơn. Bây giờ người ta muốn thêm 
  thực đơn mới thì làm thế nào. Ở đây có thể dùng builder pattern.

# Cài đặt
- Class Product: **private constructor** để việc tạo instance (thể hiện)
  của Product chỉ được thực hiện thông qua **Builder**
  (class Product là class cần tạo đối tượng, và nó phức tạp, có nhiều thuộc tính)
- Builder: là abstract class hoặc interface khai báo phương thức tạo đối tượng
- ConcreteBuilder: kế thừa Builder và cài đặt chi tiết cách tạo ra đối tượng.
  Nó sẽ xác định và nắm giữ các thể hiện mà nó tạo ra, đồng thời nó cũng cung cấp
  phương thức để trả các các thể hiện mà nó đã tạo ra trước đó
- Có thể gộp Builder và ConcreteBuilder, khi đó có thể tạo 1 private class Builder
  trong class Product. Và class Builder phải có các thuộc tính giống như của Product

# Sử dụng khi nào
- Tạo một đối tượng phức tạp: có nhiều thuộc tính (nhiều hơn 4)
  và một số bắt buộc (requried), một số không bắt buộc (optional).
- Khi có quá nhiều hàm constructor, bạn nên nghĩ đến Builder

# Lợi ích
- Hỗ trợ, loại bớt việc phải viết nhiều constructor, không cần truyền giá trị null
  cho các tham số không sử dụng.
- Code dễ đọc, dễ bảo trì hơn khi số lượng thuộc tính (propery) bắt buộc để tạo
  một object từ 4 hoặc 5 propery
- Ít bị lỗi do việc gán sai tham số khi mà có nhiều tham số trong constructor
- Có thể tạo đối tượng immutable

# Nhược điểm
- Duplicate code khá nhiều: do cần phải copy tất cả các thuộc tính từ class ban đầu
  sang class Builder.
- Tăng độ phức tạp của code (tổng thể) do số lượng class tăng lên (cần thêm 1 class
  Builder)

# So sánh Builder Pattern với Factory/ Abstract Factory Pattern
- Đều sử dụng để xây dựng một đối tượng phức tạp
- Builder cung cấp cho bạn nhiều quyền kiểm soát hơn đối với quá trình tạo đối tượng.
- Trong Builder Pattern, đối tượng được xây dựng từng bước. Nó sẽ bắt đầu từ bước 1
  và sẽ đi lên tối đa bước n và bước cuối cùng là trả về đối tượng. Nhưng trong
  Factory Pattern, bạn sẽ không thấy được đối tượng phức tạp được tạo như thế nào,
  nó không có từng bước xây dựng đối tượng
