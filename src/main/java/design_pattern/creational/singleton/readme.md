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

# VD
- java.lang.Runtime, java.awt.Desktop
  (Luôn chỉ có nhiều nhất 1 object của class Runtime, Desktop... được tạo)
- Spring's default scope: mặc định sẽ là singleton (VD như các bean được
  annotate bởi: @Service, @Repository, @Controller...). Thường thì các dự án
  sẽ dùng Service là interface, và class implement Service đó sẽ được annotate
  là @Service, tức là chỉ có 1 instance của interface đó. Nếu muốn có nhiều hơn
  cũng được thôi, tạo nhiều class khác nhau implement interface đó. Nhưng khi đó
  sẽ ko còn là singleton nữa 

# Cài đặt
- **private constructor** để ngăn cản việc tạo instance (thể hiện) của class từ các class khác.
- Đặt **private static final variable** đảm bảo biến chỉ được khởi tạo trong class,
  nó là instance duy nhất của class
- Có một method **public static** để return **instance** được khởi tạo ở trên

# Sử dụng khi nào
- Giải quyết các bài toán cần truy cập vào các ứng dụng như: Shared resource,
  Logger, Configuration, Caching, Thread pool,...
- Singleton Pattern cũng được sử dụng trong các Design Pattern khác như
  Abstract Factory, Builder, Prototype, Facede...
