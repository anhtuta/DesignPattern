# Intro

Đôi khi ta mong muốn có những đối tượng cần tồn tại duy nhất và có thể
truy xuất mọi lúc mọi nơi.

=> Chúng ta có thể nghĩ tới việc sử dụng một biến toàn cục
(global variable : **public static final**). Tuy nhiên, việc sử dụng biến
toàn cục nó phá vỡ quy tắc của OOP (encapsulation). Để giải bài toán trên,
người ta hướng đến một giải pháp là sử dụng Singleton pattern.

# Singleton Pattern là gì

- Singleton là 1 trong 5 design pattern của nhóm Creational Design Pattern
- Singleton đảm bảo chỉ duy nhất một thể hiện (instance) được tạo ra và
  nó sẽ cung cấp cho bạn một method để có thể truy xuất được thể hiện
  duy nhất đó mọi lúc mọi nơi trong chương trình

# Có sắn trong Java

- java.lang.Runtime, java.awt.Desktop
  (Luôn chỉ có nhiều nhất 1 object của class Runtime, Desktop... được tạo)
- Spring's default scope: mặc định sẽ là singleton (VD như các bean được
  annotate bởi: @Service, @Repository, @Controller...). Thường thì các dự án
  sẽ dùng Service là interface, và class implement Service đó sẽ được annotate
  là @Service, tức là chỉ có 1 instance của interface đó. Nếu muốn có nhiều hơn
  cũng được thôi, tạo nhiều class khác nhau implement interface đó. Nhưng khi đó
  sẽ ko còn là singleton nữa

# How to implement?

- **private constructor** để ngăn cản việc tạo instance (thể hiện) của class từ các class khác.
- Đặt **private static final variable** đảm bảo biến chỉ được khởi tạo trong class,
  nó là instance duy nhất của class
- Có một method **public static** để return **instance** được khởi tạo ở trên

# When to use?

- Giải quyết các bài toán cần truy cập vào các ứng dụng như: Shared resource,
  Logger, Configuration, Caching, Thread pool,...
- Singleton Pattern cũng được sử dụng trong các Design Pattern khác như
  Abstract Factory, Builder, Prototype, Facede...

# Những cách implement Singleton

1. [Eager initialization](./EagerInitializedSingleton.java)

- Khởi tạo instance ngay khi class được load
- Đây là cách dễ nhất
- Nhược điểm: instance luôn được khởi tạo dù có thể sẽ không dùng tới

2. [Static block initialization](./StaticBlockSingleton.java)

Giống như Eager initialization, chỉ khác phần **static block** cung cấp thêm lựa chọn cho việc handle exception hay các xử lý khác.

3. [Lazy Initialization](./LazyInitializedSingleton.java)

- Khắc phục Eager initialization: khi nào gọi `getInstance()` thì instance mới được khởi tạo
- Nhược điểm
  - KHÔNG thread-safe: nếu nhiều thread cùng gọi thì nhiều instance được tạo
  - Nếu method `getInstance()` quá chậm thì lần gọi đầu tiên phải chờ lâu

4. [Thread Safe Singleton](./ThreadSafeSingleton.java)

- Giống Lazy Initialization nhưng method `getInstance` là `synchronized`
- Ưu: thread-safe, khi nào gọi `getInstance()` thì instance mới được khởi tạo
- Nhược: `synchronized` rất chậm và tốn hiệu năng (reduce the performance) => Cải thiện ở cách dưới

5. [Double Check Locking Singleton](./DoubleCheckLockingSingleton.java)

- Check 2 lần xem instance đã tồn tại chưa
- Phải khai báo `volatile` cho instance để tránh lớp làm việc không chính xác do quá trình tối ưu hóa của trình biên dịch.

```java
public static DoubleCheckLockingSingleton getInstance() {
    // Do something before get instance ...
    if (instance == null) {
        // Do the task too long before create instance ...
        // Block so other threads cannot come into while initialize
        synchronized (DoubleCheckLockingSingleton.class) {
            // Re-check again. Maybe another thread has initialized before
            if (instance == null) {
                instance = new DoubleCheckLockingSingleton();
            }
        }
    }
    // Do something after get instance ...
    return instance;
}
```

6. [Bill Pugh Singleton](./BillPughSingleton.java) (pugh /pjuː/)

- Tạo static nested class để tạo mới instance
- Là cách thường **hay được sử dụng** và **hiệu quả nhất** (theo các chuyên gia đánh giá 🙂).
- Khi Singleton được tải vào bộ nhớ thì SingletonHelper chưa được tải vào. Nó chỉ được tải khi và chỉ khi phương thức getInstance() được gọi
  - Tránh được lỗi chưa gọi `getInstance` đã khởi tạo instance
  - Thread-safe
  - Ko dùng `synchronized` nên performance cao

# Nên chọn cách implement nào?

- Single-thread: `LazyInitializedSingleton`
- Multi-thread: `BillPughSingleton`, `DoubleCheckLockingSingleton`
