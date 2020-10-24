# Factory Pattern là gì
- Factory Pattern là một mẫu thiết kế tạo dựng (Creation Pattern), 
- Nó được sử dụng rộng rãi trong JDK và các Framework như Spring, Struts.
- Nhiệm vụ của Factory Pattern là quản lý và trả về các đối tượng theo yêu cầu,
  giúp cho việc khởi tạo đổi tượng một cách linh hoạt hơn
- Factory Pattern đúng nghĩa là một nhà máy, và nhà máy này sẽ "sản xuất"
  các đối tượng theo yêu cầu của chúng ta
- Factory Pattern được sử dụng khi có một class cha (super-class) với
  nhiều class con (sub-class), dựa trên đầu vào và phải trả về 1 trong
  những class con đó.

# Ví dụ
- java.util.Calendar, NumberFormat
- Method valueOf() trong một số wrapper class như Boolean, Integer, Double…
- BeanFactory trong Spring Framework.
- SessionFactory trong Hibernate Framework

# Cài đặt
- Super Class: có thể là một interface, abstract class hay một class thông thường.
- Sub Class**es**: sẽ **implement** các phương thức của supper class
  theo nghiệp vụ riêng của nó.
- Factory Class: chịu tránh nhiệm **khởi tạo** các đối tượng sub class
  dựa theo **tham số đầu vào**. Lưu ý: lớp này là **Singleton** hoặc cung cấp một
  **public static method** cho việc truy xuất và khởi tạo đối tượng. Factory class
  sử dụng if-else hoặc switch-case để xác định class con đầu ra

# Sử dụng khi nào
- Chúng ta có một **super class** với nhiều **class con** và dựa trên **đầu vào**,
  chúng ta cần trả về một class con. Mô hình này giúp chúng ta đưa trách nhiệm của việc
  khởi tạo sang lớp **Factory**
- Sau này nếu cần mở rộng thêm những lớp con khác: chỉ cần tạo sub class và
  implement thêm vào factory method cho việc khởi tạo sub class này

# Lợi ích
- Giúp chuơng trình độc lập với những class cụ thể mà chúng ta cần tạo 1 object
  (Vì việc tạo object là do Factory quản lý)
- Mở rộng code dễ dàng hơn: khi cần mở rộng, chỉ việc tạo ra sub class và
  implement thêm vào factory method
- Khởi tạo các Objects mà che giấu đi xử lí logic của việc khởi tạo đấy
- Dễ dạng quản lý life cycle của các Object được tạo bởi Factory Pattern

# Ref:
https://gpcoder.com/4352-huong-dan-java-design-pattern-factory-method/
