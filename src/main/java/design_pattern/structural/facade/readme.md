# Facade Pattern là gì (/fəˈsɑːd/)
- Facade Pattern là một mẫu thiết kế cấu trúc (Structural Pattern)
- Pattern này cung cấp một giao diện chung đơn giản thay cho một nhóm các
  giao diện có trong một hệ thống con (subsystem). Facade Pattern định nghĩa
  một giao diện ở một cấp độ cao hơn để giúp cho Client có thể dễ dàng
  sử dụng hệ thống con này (Xem ảnh sẽ thấy Client và Subsystem).
- Facade Pattern cho phép các đối tượng truy cập trực tiếp giao diện chung
  này để giao tiếp với các giao diện có trong hệ thống con. Mục tiêu là che
  giấu các hoạt động phức tạp bên trong hệ thống con, làm cho hệ thống
  con dễ sử dụng hơn

# Cài đặt
- Facade: biết rõ lớp của hệ thống con nào đảm nhận việc đáp ứng yêu cầu của
  client, sẽ chuyển yêu cầu của client đến các đối tượng của hệ thống con
  tương ứng. Các đối tượng Facade thường là **Singleton** bởi vì chỉ cần duy nhất
  một đối tượng Facade
- Subsystem: cài đặt các chức năng của hệ thống con, xử lý công việc được gọi
  bởi Facade. Các lớp này không cần biết Facade và không tham chiếu đến nó
- Client: đối tượng sử dụng Facade để tương tác với các subsystem

# Sử dụng khi nào
- Khi hệ thống có **rất nhiều lớp** làm người sử dụng rất khó để có thể hiểu được
  quy trình xử lý của chương trình. Và khi có **rất nhiều hệ thống con** mà mỗi
  hệ thống con đó lại có những giao diện riêng lẻ của nó nên rất khó cho việc
  sử dụng phối hợp. Khi đó có thể sử dụng **Facade Pattern** để tạo ra một giao diện
  đơn giản cho người sử dụng một hệ thống phức tạp.
- Khi người sử dụng phụ thuộc nhiều vào các lớp cài đặt. Việc áp dụng Facade Pattern
  sẽ **tách biệt** hệ thống con của người dùng và các hệ thống con khác, do đó
  tăng khả năng **độc lập** và **khả chuyển** của hệ thống con, dễ chuyển đổi
  nâng cấp trong tương lai.
- Khi bạn muốn **phân lớp** các hệ thống con. Dùng Facade Pattern để định nghĩa **cổng
  giao tiếp chung** cho mỗi hệ thống con, do đó giúp **giảm sự phụ thuộc** của các
  hệ thống con vì các hệ thống này chỉ giao tiếp với nhau thông qua các
  cổng giao diện chung đó.
- Khi bạn muốn bao bọc, **che giấu tính phức tạp** trong các hệ thống con
  đối với phía Client.

# Lợi ích
- Giúp cho hệ thống của bạn trở nên đơn giản hơn trong việc sử dụng và trong
  việc hiểu nó, vì một mẫu Facade có các phương thức tiện lợi cho các tác vụ chung
- Giảm sự phụ thuộc của các mã code bên ngoài với hiện thực bên trong của thư viện,
  vì hầu hết các code đều dùng Facade, vì thế cho phép sự linh động trong
  phát triển các hệ thống

# Ref:
https://gpcoder.com/4604-huong-dan-java-design-pattern-facade/
