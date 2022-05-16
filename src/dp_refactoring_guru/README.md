# Design pattern

Note trong file này:

- Interface có thể thay là abstract class, interface trường hợp này muốn nói tới một kiểu trừu tượng chứ ko phải cụ thể (concrete)

# 1. Creational patterns

Các object muốn khởi tạo sẽ gọi là Product. Thường thì Product sẽ là kiểu abstract

## 1.1. Factory method

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

![](./factory_method/factory-method-structure.png)

### Code example

[Xem ở đây](./factory_method/ui_elements/)

### When to use?

- **Khi bạn chưa biết trước chính xác kiểu concrete object nào bạn muốn làm việc**
  - Factory Method tách riêng code khởi tạo Product và code logic xử lý Product, do đó ta dễ dàng mở rộng, thêm mới các kiểu Product cụ thể
- Khi bạn muốn tạo thư viện/framework, cho phép user có thể mở rộng các internal component
- Khi bạn muốn sử dụng lại các object hiện có thay vì tạo mới

### Pros and Cons

Pros:

- **Tránh được tight coupling** giữa Creator và các concrete Product
- Tuân thủ **Single Responsibility Principle**: move đoạn code khởi tạo các Product ra riêng 1 chỗ
- Tuân thủ **Open/Closed Principle**: sau này nếu cần mới các kiểu concrete Product, ko cần sửa đổi Client code (đoạn code business logic xử lý Product, trong ví dụ trên là method [renderWindow()](./factory_method/ui_elements/Dialog.java))

Cons: Code khá phức tạp do có nhiều class con. Do đó, the best case scenario is when you’re introducing the pattern into an existing hierarchy of creator classes.

### Relations with Other Patterns

- Sau khi biết pattern này, bạn có thể đọc tiếp các pattern Abstract Factory, Prototype, và Builder (more flexible, but more complicated)
- Abstract Factory thường dựa trên pattern này

(Not done yet. TODO: update nốt sau khi hiểu rõ các pattern khác)

### Có sẵn trong Java

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
- https://sourcemaking.com/design_patterns/factory_method

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

## 1.2. Abstract factory

### Intent

Cho phép tạo ra các **family of related objects** (họ các đối tượng liên quan đến nhau) mà ko cần chỉ rõ các concrete class của chúng

**Family of related objects** là gì? Nó là tập các object liên quan tới nhau, ví dụ như tập các abstract sau: `Chair` + `Sofa` + `CoffeeTable`. Các biến thể concrete của tập này có thể là

- `ArtDecoChair` + `ArtDecoSofa` + `ArtDecoCoffeeTable` (biến thể ArtDeco)
- `VictorianChair` + `VictorianSofa` + `VictorianCoffeeTable` (biến thể Victorian)
- `ModernChair` + `ModernSofa` + `ModernCoffeeTable` (biến thể Modern)

![](./abstract_factory/furniture_shop/product-families-and-their-variants.png)

Mỗi biến thể trên được gọi là **variant of a product family** (biến thể của 1 họ các Product)

Tức là, common interface Product**s** của bạn sẽ gồm 3 interface `Chair` + `Sofa` + `CoffeeTable`, và Client có thể tạo các concrete class của tập này là 3 tập trên, và chú ý là sẽ KHÔNG có trường hợp mix, chẳng hạn `ArtDecoChair` + `VictorianSofa` + `VictorianCoffeeTable` hay `ModernChair` + `ArtDecoSofa` + `ModernCoffeeTable`.

Nếu chương trình của bạn ko dùng tới family of related objects, thì bạn ko cần dùng pattern **Abstract factory**

> And again, a lot of people mix-up the **abstract factory** pattern with a **simple factory** class declared as abstract. Don't do that!

(Hãy tưởng tượng bạn đang thiết kế một game thế giới mở cực hoành tráng, và hiện tại dự án đang đến đoạn thiết kế furniture shop :v)

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
  // Factory cho biến thể ArtDeco: CHỈ CÓ THỂ tạo các object thuộc kiểu ArtDeco
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

### Code example

[Xem ở đây](./abstract_factory/furniture_shop/)

### So sánh Abstract factory (AF) vs Factory method (FM)

- AF gồm tập family of products, còn FM chỉ có 1 interface Product

  ![common-interface-product](https://user-images.githubusercontent.com/26838239/168205118-39643910-e0e3-4c6a-adaa-37d9bd5b3d2b.png)

- Factory của AF giống với Creator của FM, cả 2 đều là abstract và cả 2 pattern đều chỉ có 1 thành phần này. Tuy nhiên, factory của AF có nhiều **factory method** vì AF có nhiều Product, trong khi FM chỉ có 1

  ![image](https://user-images.githubusercontent.com/26838239/168205450-2b40e159-bd3c-4e1b-aeee-02bef1ed6211.png)

- Concrete factories của AF giống với concrete creators của FM

  ![image](https://user-images.githubusercontent.com/26838239/168205908-639d816b-2e4d-4dc9-aae5-1afe8e03adfc.png)

### When to use?

- Khi bạn phải làm việc với nhiều tập **family of related products**, nhưng bạn ko muốn phụ thuộc vào các concrete Product của tập đó
  - Pattern này giúp bạn tránh được việc tạo ra các biến thể ko đúng (biến thể gồm các Product khác kiểu với nhau), chẳng hạn: `ArtDecoChair` + `ModernCoffeeTable`
  - Khi có nhiều kiểu Product, bạn nên **extract từng factory method thành từng factory class riêng biệt**

### Pros and Cons

Pros:

- Các Product được tạo bởi 1 factory sẽ tương thích với nhau (vì chúng cùng variant)
- Tránh được tight coupling giữa concrete Products và Client code
- Tuân thủ **Single Responsibility Principle**: move đoạn code khởi tạo các Product ra riêng 1 chỗ
- Tuân thủ **Open/Closed Principle**: sau này nếu cần mới các biến thể của các Product, ko cần sửa đổi Client code (ex: chỉ cần thêm mới Factory và khai báo mới các Product, còn code business logic được xử lý trong method [testFurniture()](./abstract_factory/furniture_shop/App.java))

Cons: code trở lên phức tạp hơn vì có nhiều interface và class

### Relations with Other Patterns

- Các class của **Abstract Factory** thường dựa trên 1 tập các **Factory Methods**
- ...

### Ref

- https://refactoring.guru/design-patterns/abstract-factory
- https://refactoring.guru/design-patterns/factory-comparison

## 1.3. Builder

### Intent

- Giúp khởi tạo object phức tạp (gồm rất nhiều thuộc tính) từng bước một
- Giúp khởi tạo các object khác nhau, KHÔNG có chung common interface

### Problem

Giả sử class `House` của bạn có rất nhiều thuộc tính, việc khởi tạo các object House với các thuộc tính khác nhau sẽ cho ra kiểu nhà khác nhau, chẳng hạn nhà bên trái chỉ cần garage, do đó ko cần các params `hasSwimPool`, `hasStatues`, `hasGarden`. Trong khi nhà bên phải cần đủ các thứ như vậy

![](./builder/house.png)

Trong nhiều trường hợp, các params sẽ ko cần thiết, và sẽ phải truyền null vào, khiến constructor trông thật xấu xí! Giả sử bạn cần xây 10 ngôi nhà, mà chỉ có 1 ngôi nhà có bể bơi, thì 9 ngôi nhà còn lại, param `hasSwimPool` sẽ bị thừa

Bạn có thể tạo nhiều constructor khác nhau, kiểu như:

```java
class House {
    public House() {...}
    public House(int windows, int doors, int rooms) {...}
    public House(int windows, int doors, int rooms, boolean hasGarage) {...}
    public House(int windows, int doors, int rooms, boolean hasGarage, boolean hasSwimPool, boolean hasStatues, boolean hasGarden) {...}
}
```

Việc tạo nhiều constructor như vậy chỉ khả thi với các ngôn ngữ hỗ trợ method overloading, chẳng hạn Java, C#

Với class nhiều property như vậy, tốt nhất nên tách việc khởi tạo object sang class khác tên là `Builder`

### How to implement?

- Tạo base interface `Builder` gồm nhiều step giúp tạo object Product

  ![](./builder/house-builder.png)

- Để tạo object, bạn thực thi từng step đó và KHÔNG nhất thiết phải thực thi hết (ko có bể bơi thì ko thực thi `buildSwimPool`)

### Cấu trúc

1. Base interface **Builder**: khai báo các step để tạo object Product
2. **Concrete builders**: cung cấp các cách triển khai `Builder` khác nhau, do đó chúng có thể trả về các object khác nhau mà KHÔNG follow theo common interface
3. **Products**: các object cần được khởi tạo, và chúng KHÔNG thuộc 1 common interface nào hết!
4. **Director**: xác định thứ tự gọi các step để tạo object Product
5. Client: liên kết concrete builder với director

![](./builder/builder-structure.png)

### Stop!

Thực sự cảm thấy pattern builder trên trang refactoring_guru này có vấn đề!

1. Hãy xem 2 concrete builder sẽ thấy, chúng thực sự GIỐNG nhau

![](./builder/compare-concrete-builders.png)

Vậy thì tại sao phải tạo base builder để làm gì? Tại sao không dùng luôn 1 concrete builder?

2. Cuối cùng thì concrete builder vẫn phải gọi hàm constructor full param của Product để tạo object

```java
public Car getResult() {
    return new Car(type, seats, engine, transmission, tripComputer, gpsNavigator);
}
```

Vậy nếu sau này class Car có thêm các field mới thì sao? Lại sửa constructor này à, rồi sửa cả method `getResult()` trong builder? Hay là tạo thêm các constructor mới với số lượng param khác nhau?

Có thể ý tưởng của trang này khác với các kiểu builder thông dụng!

Hãy dẹp refactoring_guru sang một bên và xét kiểu pattern builder đơn giản hơn [tại đây](../design_pattern/creational/builder/Person.java)

### Ref

https://refactoring.guru/design-patterns/builder

TODO: nếu sau này thấy các implement này có ích, hãy quay lại và note lại theo trang refactoring_guru

## 1.4. Prototype

### Tên gọi khác

Clone

### Intent

Giúp bạn copy 1 object. Đối tượng mới là 1 bản sao giống 100% đối tượng gốc, ta có thể thay đổi dữ liệu của object mới mà ko làm ảnh hưởng đến object gốc

### How to implement?

Trong Java cung cấp mẫu prototype pattern này bằng việc implement interface `Cloneable` và sử dụng method `clone()` để tạo object có đầy đủ thuộc tính của đối tượng ban đầu

Nếu ko code Java thì có thể implement như sau:

- Tạo interface `Prototype` và khai báo method `clone`
- Class Product muốn clone được phải implement Prototype trên và phải có 1 constructor với param thuộc chính kiểu Product
- Trong constructor đó, copy toàn bộ field từ param Product vào 1 instance Product mới

### When to use?

- Prototype Pattern được dùng khi việc tạo một object tốn nhiều chi phí và thời gian trong khi bạn đã có một object tương tự tồn tại
- Khi muốn tạo bản sao của object nhưng object đó có các field private ko truy cập được

### Code example

[Xem ở đây](../design_pattern/creational/prototype/gpcoder/chessboard/README.md)

### Ref

- https://refactoring.guru/design-patterns/prototype
- https://gpcoder.com/4413-huong-dan-java-design-pattern-prototype/

## 1.5. Singleton

### Intent

Singleton đảm bảo chỉ duy nhất một thể hiện (instance) được tạo ra trong suốt chương trình

Trang refactoring_guru nói ko đủ lắm, xem thêm [tại đây](../design_pattern/creational/singleton/readme.md)

# 2. Structural patterns

## 2.1. Adapter

### Tên gọi khác

Wrapper

### Intent

Cho phép các interface ko tương thích có thể làm việc với nhau

Giúp convert interface A (Client ko giao tiếp được) -> interface B (Client có thể hiểu được)

### Có sẵn trong Java

- `java.util.Arrays#asList()`
- `java.util.Collections#list()`
- `java.util.Collections#enumeration()`
- `java.io.InputStreamReader(InputStream)` (returns a Reader object)
- `java.io.OutputStreamWriter(OutputStream)` (returns a Writer object)
- `javax.xml.bind.annotation.adapters.XmlAdapter#marshal()` and `#unmarshal()`
