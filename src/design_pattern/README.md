# Design pattern

Note trong file này:

- Interface có thể thay là abstract class, interface trường hợp này muốn nói tới một kiểu trừu tượng chứ ko phải cụ thể (concrete)

# 1. Creational patterns

## Factory method

### Tên gọi khác

Virtual Constructor. Note: pattern này khác với **Simple Factory** pattern

### Intent

- Định nghĩa 1 interface để khởi tạo object ở class cha, nhưng để class con ghi đè (alter) kiểu object sẽ được khởi tạo (note: interface có thể thay bằng 1 abstract class)
- (Theo ý hiểu cá nhân t thì interface đó sẽ có 1 method tạo object Product (Product cũng là kiểu abstract), việc của class con là ghi đè method đó để tạo ra 1 kiểu concrete class của Product mà nó muốn)

Nếu bạn có 1 method khởi tạo object ở base class, và subclass extend nó và override lại method đó, thì bạn đang dùng **Factory method**!

### How to implement?

Thay vì khởi tạo trực tiếp 1 object cụ thể của interface Product bằng toán tử `new`, ta thay bằng 1 abstract method và method đó return kiểu Product. Class con sẽ phải ghi đè method đó để khởi tạo 1 concrete class Product mà nó muốn. Abstract method này được gọi là **factory method**. Ex:

```java
interface Product {}

public abstract class Client {
    public void doSomething() {
        // KHÔNG tạo mới product bằng new
        // Product prd = new Product();

        // Mà ta sẽ tạo mới Product bằng 1 factory method
        Product prd = createProduct();
        processProduct(prd);  // Do something with created product
    }

    // Đây chính là factory method, class con cần override
    public abstract Product createProduct();
}
```

### Cấu trúc

Gồm các thành phần sau:

1. **Common interface Product**: chính là interface mà chúng ta dùng Factory method pattern để khởi tạo thay vì dùng `new`, cũng chính là kiểu mà class con muốn tự tạo class cụ thể
2. **Concrete Products**: các lớp cụ thể của Product
3. Base **Creator**: Đây sẽ là khai báo **factory method**. Note: **kiểu trả về của factory method phải là kiểu Product, và method đó phải là abstract**.

   Note: công việc chính của `Creator` KHÔNG phải là khởi tạo object Product, dù nó tên là Creator. Thường thì class này sẽ xử lý business logic code liên quan đến các object Product

4. **Concrete Creators**: kế thừa từ Creator do đó nó có thể tạo các kiểu Product khác nhau

![](./creational/factory_method/factory-method-structure.png)

### Code example

[Xem ở đây](./creational/factory_method/refactoringguru/ex1_ui_elements/)

### When to use?

- **Khi bạn chưa biết trước chính xác kiểu concrete object nào bạn muốn làm việc**
  - Factory Method tách riêng code khởi tạo Product và code logic xử lý Product, do đó ta dễ dàng mở rộng, thêm mới các kiểu Product cụ thể
- Khi bạn muốn tạo thư viện/framework, cho phép user có thể mở rộng các internal component
- Khi bạn muốn sử dụng lại các object hiện có thay vì tạo mới

### Pros and Cons

Pros:

- **Tránh được tight coupling** giữa Creator và các concrete Product
- Tuân thủ **Single Responsibility Principle**: move đoạn code khởi tạo các Product ra riêng 1 chỗ
- Tuân thủ **Open/Closed Principle**: sau này nếu cần mới các kiểu concrete Product, ko cần sửa đổi Client code (đoạn code business logic xử lý Product, trong ví dụ trên là method [renderWindow()](./creational/factory_method/refactoringguru/ex1_ui_elements/Dialog.java))

Cons: Code khá phức tạp do có nhiều class con. Do đó, the best case scenario is when you’re introducing the pattern into an existing hierarchy of creator classes.

### Relations with Other Patterns

- Sau khi biết pattern này, bạn có thể đọc tiếp các pattern Abstract Factory, Prototype, và Builder (more flexible, but more complicated)
- Abstract Factory thường dựa trên pattern này

(Not done yet. TODO: update nốt sau khi hiểu rõ các pattern khác)

### Có sẵn trong Java:

- `java.util.Calendar#getInstance()`
- `java.text.NumberFormat#getInstance()`
- `java.nio.charset.Charset#forName()`
- `java.util.ResourceBundle#getBundle()`
- `java.net.URLStreamHandlerFactory#createURLStreamHandler(String)` (Returns different singleton objects, depending on a protocol)
- `java.util.EnumSet#of()`
- `javax.xml.bind.JAXBContext#createMarshaller()` and other similar methods.

### Ref

- https://refactoring.guru/design-patterns/factory-method
- https://refactoring.guru/design-patterns/factory-method/java/example
- https://sourcemaking.com/design_patterns/factory_method (TODO: đọc thêm mục Rules of thumb)

## Simple factory

### Intent

- Định nghĩa 1 class có 1 method khởi tạo object Product dựa theo param được truyền vào
- Đây chính là pattern được giới thiệu trong cuốn **Head First Design Patterns**, [**Design pattern for dummies**](../dp_for_dummies/README.md#factory-pattern-definition)

(Note: `Product` ở đây là 1 kiểu abstract mà ta muốn khởi tạo (như đã nói ở pattern bên trên))

### How to implement?

Thông thường, pattern này chỉ có 1 method bên trong 1 class, method này sẽ nhận params rồi trả về 1 object Product dựa theo param đó. Ex:

```java
class UserFactory {
    public static function create(String type) {
        switch (type) {
            case 'user': return new User();
            case 'customer': return new Customer();
            case 'admin': return new Admin();
            default:
                throw new Exception('Wrong user type passed.');
        }
    }
}
```

### Pros and Cons

Cons: method `create` này sẽ phình to dần nếu sau này cần thêm mới nhiều kiểu object Product. Các khắc phục đơn giản là bóc tách method `create` đó thành các subclass nhỏ hơn => nó sẽ trở thành **Factory method** pattern!

### Ref

https://refactoring.guru/design-patterns/factory-comparison

## Abstract factory

### Intent

Cho phép tạo ra các **family of related objects** (họ các đối tượng liên quan đến nhau) mà ko cần chỉ rõ các concrete class của chúng

**Family of related objects** là gì? Nó là tập các object liên quan tới nhau, ví dụ như tập các abstract sau: `Chair` + `Sofa` + `CoffeeTable`. Các biến thể concrete của tập này có thể là

- `ArtDecoChair` + `ArtDecoSofa` + `ArtDecoCoffeeTable` (biến thể thuộc họ ArtDeco)
- `VictorianChair` + `VictorianSofa` + `VictorianCoffeeTable` (biến thể thuộc họ Victorian)
- `ModernChair` + `ModernSofa` + `ModernCoffeeTable` (biến thể thuộc họ Modern)

![](./creational/abstract_factory/product-families-and-their-variants.png)

Mỗi biến thể trên được gọi là **variant of a product family** (biến thể của 1 họ các Product)

Tức là, common interface Product**s** của bạn sẽ gồm 3 interface `Chair` + `Sofa` + `CoffeeTable`, và Client có thể tạo các concrete class của tập này là 3 tập trên, và chú ý là sẽ KHÔNG có trường hợp mix, chẳng hạn `ArtDecoChair` + `VictorianSofa` + `VictorianCoffeeTable` hay `ModernChair` + `ArtDecoSofa` + `ModernCoffeeTable`.

Nếu chương trình của bạn ko dùng tới family of related objects, thì bạn ko cần dùng pattern **Abstract factory**

### How to implement?

- Khai báo từng kiểu common interface Product (vd trên có 3 Product: `Chair`, `Sofa`, `CoffeeTable`)
- Tạo các kiểu concrete Product cho các interface trên, ex:
  ```java
  public interface Chair {}
  public class ArtDecoChair implements Chair {}
  ```
- Khai báo **Abstract Factory**, bao gồm các **factory method** dùng để khởi tạo các interface trên (factory method, như nói ở pattern trước, chỉ đơn giản là method return kiểu Product), ex:
  ```java
  public interface FurnitureFactory {
      public abstract Chair createChair();
      public abstract Sofa createSofa();
      public abstract CoffeeTable createCoffeeTable();
  }
  ```
- Tạo từng concrete factory cho từng biến thể của họ Product, ex:
  ```java
  // Factory cho biến thể ArtDeco: CHỈ CÓ THỂ tạo các object thuộc họ ArtDeco
  public class ArtDecoFactory implements FurnitureFactory {
      public abstract Chair createChair() {
          return new ArtDecoChair();
      }
      public abstract Sofa createSofa() {
          return new ArtDecoSofa();
      }
      public abstract CoffeeTable createCoffeeTable() {
          return new ArtDecoCoffeeTable();
      }
  }
  ```

Với các làm trên, bất kỳ biến thể nào của `Chair` được return từ concrete factory, nó sẽ luôn match với kiểu `Sofa` và `CoffeeTable` được sản xuất bởi factory đó

### Cấu trúc

1. **Abstract Products**: khai báo các kiểu interface cho family of product (tập các product liên quan đến nhau)
2. **Concrete Products**: concrete Products, được nhóm thành các **variant of a product family** (các biến thể của tập các Product) khác nhau. (Tức là: 1 tập biến thể gồm nhiều concrete Product)
3. **Abstract Factory**: một interface khai báo các factory method cho việc khởi tạo các Product. Note: các method này phải return các **abstract** Product chứ ko phải các concrete Product
4. **Concrete Factories**: implement Abstract Factory, chúng sẽ override các factory method để tạo ra các concrete Product **cùng loại** (cùng family)

### So sánh Abstract factory (AF) vs Factory method (FM)

- AF gồm tập family of products, còn FM chỉ có 1 interface Product

  ![common-interface-product](https://user-images.githubusercontent.com/26838239/168205118-39643910-e0e3-4c6a-adaa-37d9bd5b3d2b.png)
- Factory của AF giống với Creator của FM, cả 2 đều là abstract và cả 2 pattern đều chỉ có 1 thành phần này. Tuy nhiên, factory của AF có nhiều **factory method** vì AF có nhiều Product, trong khi FM chỉ có 1

  ![image](https://user-images.githubusercontent.com/26838239/168205450-2b40e159-bd3c-4e1b-aeee-02bef1ed6211.png)
- Concrete factories của AF giống với concrete cretors của FM

  ![image](https://user-images.githubusercontent.com/26838239/168205908-639d816b-2e4d-4dc9-aae5-1afe8e03adfc.png)

