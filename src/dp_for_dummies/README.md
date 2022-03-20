## Design patterns

Developers spend much more time **extending and changing code** than they do originally developing it (các dev thường dành nhiều time để mở rộng và sửa đổi code hơn là phát triển code mới)

Design patterns are intended to help you **handle change** as you have to adapt your code to new and unforeseen circumstances

## New words

- Polymorphism: tính đa hình
- Composition: tính tổng hợp, kết hợp (1 class sẽ gồm nhiều object khác)
- Inheritance: tính kế thừa (1 class extends từ 1 class khác)
- Base class: lớp cơ sở
- Derived class: lóp dẫn xuất (kế thừa từ base class)
- freestanding (adj): đứng 1 mình
- volatile /ˈvɑː.lə.t̬əl/ (adj): dễ thay đổi (change rapidly and unpredictably)
- interchangeable (adj): có thể hoán đổi lẫn nhau
- Runtime: là giai đoạn mà chương trình đang chạy (đang thực thi). VD như `RuntimeException` là `Exception` xảy ra trong thời gian chương trình chạy, chẳng hạn `ArrayIndexOutOfBoundsException`, `NullPointerException`, `ArithmaticException` là các ngoại lệ chỉ xảy ra tại lúc runtime

## Chapter 2: Putting Plans into Action with the Strategy Pattern

Design insight: **Separate the parts of your code that will change the most from the rest of your application. And always try to reuse those parts as much as possible.**

### 2.1. Inheritance vs Composition

Polymorphism (đa hình) thường phát huy tác dụng khi bạn làm việc với design pattern vì design pattern có xu hướng thiên về composition hơn là inheritance. (Composition (tổng hợp, kết hợp) nghĩa là khi object của bạn chứa các object khác thay vì kế thừa từ chúng.

Design pattern-oriented programming (Lập trình hướng mẫu thiết kế) thường thích composition hơn là inheritance. Khi bạn sử dụng composition, code của bạn chứa các đối tượng khác, thay vì kế thừa từ chúng.

### 2.2. Bài toán thiết kế Vehicle

Xét ví dụ sau, giả sử bạn muốn thiết kế các Vehicle (phương tiện), ví dụ này tập trung vào method `go` thôi, mỗi phương tiện đều phải có method `go`, do đó ta tạo 1 `abstract class` như sau:

```java
abstract class Vehicle {
    public Vehicle() {}

    public void go() {
        System.out.println("Now I'm driving.");
    }
}
```

Lúc này bạn muốn thiết kế 2 phương tiện là xe đua đường phố và xe đua công thức 1, chúng đều có chung method `go`, do đó bạn nghĩ đến việc cho 2 sản phẩm đó kế thừa class trên:

```java
class StreetRacer extends Vehicle {
}

class FormulaOne extends Vehicle {
}
```

Mọi thứ trông khá ổn. Lúc này bạn lại phải thiết kế thêm sản phẩm thứ 3 là máy bay trực thăng, và thứ 4 là máy bay phản lực:

```java
class Helicopter extends Vehicle {
}

class Jet extends Vehicle {
}

public class StartTheRace {
    public static void main(String[] args) {
        StreetRacer streetRacer = new StreetRacer();
        FormulaOne formulaOne = new FormulaOne();
        Helicopter helicopter = new Helicopter();

        streetRacer.go();   // Now I'm driving.
        formulaOne.go();    // Now I'm driving.
        helicopter.go();    // Now I'm driving.
        jet.go();           // Now I'm driving.
    }
}
```

Trông chả ổn tý nào! Máy bay phải bay chứ, nhỉ! Và bạn nghĩ ngay tới việc `override` lại method `go` của class cha, quá đơn giản phải ko:

```java
class Helicopter extends Vehicle {
    @Override
    public void go() {
        System.out.println("Now I'm flying.");
    }
}

class Jet extends Vehicle {
    @Override
    public void go() {
        System.out.println("Now I'm flying super fast!.");
    }
}

public class StartTheRace {
    public static void main(String[] args) {
        StreetRacer streetRacer = new StreetRacer();
        FormulaOne formulaOne = new FormulaOne();
        Helicopter helicopter = new Helicopter();
        Jet jet = new Jet();

        streetRacer.go();   // Now I'm driving.
        formulaOne.go();    // Now I'm driving.
        helicopter.go();    // Now I'm flying.
        jet.go();           // Now I'm flying super fast!.
    }
}
```

### 2.3. Xử lý thay đổi với `has-a` thay vì `is-a`

Thời gian trôi qua, rất nhiều thứ thay đổi và bạn phải thiết kế lại cách máy bay di chuyển rất nhiều lần (= cách update method `go` nhiều lần):

```java
class Helicopter extends Vehicle {
    @Override
    public void go() {
        System.out.println("Now I'm flying version 2.1.");
    }
}
// Tương tự với class Jet
```

Bạn cũng phải update cách di chuyển cho `StreetRacer` và `FormulaOne` nữa, và 2 loại xe này có chung cách di chuyển nên update method go của 2 thằng là như nhau, nghe có vẻ khá là `duplicated code` rồi nhỉ :v

Việc update method `go` trong bản thân class như này thực sự ko hề hay ho chút nào, thay vào đó ta nên: **Tách các phần code sẽ thay đổi nhiều nhất khỏi phần còn lại của ứng dụng và cố gắng đặt chúng ở trạng thái tự do nhất có thể (freestanding) để dễ bảo trì.** (Trong ví dụ này thì method `go` chính là phần dễ thay đổi!)

Với inheritance, các lớp cơ sở và các lớp dẫn xuất (`base classes` and `derived classes`) có mối quan hệ `is-a`, chẳng hạn: a Helicopter `is-a` Vehicle. Base class xử lý một tác vụ cụ thể theo một cách, nhưng sau đó một lớp dẫn xuất sẽ thay đổi điều đó và lớp dẫn xuất tiếp theo sẽ thay đổi mọi thứ một lần nữa. Vì vậy, bạn đang phát tán (spread out) cách bạn xử lý một nhiệm vụ qua nhiều thế hệ class

Bạn có thể trích xuất các phần dễ thay đổi (volatile) trong code và đóng gói chúng dưới dạng các đối tượng. Làm như vậy cho phép bạn tùy code bằng cách tạo ra `composites of objects` (sự tổng hợp đối tượng). Mối quan hệ giữa chúng lúc này là `has-a`: Helicopter `has-a` certain/different way of moving (Helicopter có 1 cách di chuyển nhất định)

Túm lại: **Khi có nhiều thay đổi, nên dùng quan hệ `has-a` thay vì `is-a`, và TÁCH RIÊNG CODE THƯỜNG XUYÊN THAY ĐỔI sang 1 object riêng, thay vì KẾ THỪA TỪ ĐỐNG CODE HAY THAY ĐỔI đó.**

### 2.4. Tách riêng code hay thay đổi

Remember: encapsulate code in external algorithms (các lớp GoAlgorithm) for easy use rather than spreading it around inside your core code and modifying it throughout that code (các lớp StreetRacer, Jet...).

```java
interface GoAlgorithm {
    void go();
}

// Tạo từng cách di chuyển riêng
class GoByDriving implements GoAlgorithm {
    @Override
    public void go() {
        System.out.println("Now I'm driving version 2.2.");
    }
}

class GoByFlying implements GoAlgorithm {
    @Override
    public void go() {
        System.out.println("Now I'm flying version 2.2.");
    }
}

class GoByFlyingFast implements GoAlgorithm {
    @Override
    public void go() {
        System.out.println("Now I'm flying super fast version 2.2!.");
    }
}

// Base class Vehicle lúc này cần có 1 nơi lưu trữ thuật toán di chuyển,
// ta sẽ dùng method setGoAlgorithm để các class con sẽ có thể
// tự nó xác định thuật toán di chuyển mà nó muốn
abstract class Vehicle {
    private GoAlgorithm goAlgorithm;

    public void setGoAlgorithm(GoAlgorithm goAlgorithm) {
        this.goAlgorithm = goAlgorithm;
    }

    // method go lúc này ko cần hard-code nữa! Sau này nếu muốn thay đổi gì
    // cũng ko cần update ở đây!
    public void go() {
        goAlgorithm.go();
    }
}

// Các class con muốn dùng cách di chuyển nào thì chỉ cần gọi method
// thiết lập cách di chuyển đó ở class cha. Ko còn class con nào phải
// custom lại method go nữa!
class StreetRacer extends Vehicle {
    public StreetRacer() {
        super.setGoAlgorithm(new GoByDriving());
    }
}

class FormulaOne extends Vehicle {
    public FormulaOne() {
        super.setGoAlgorithm(new GoByDriving());
    }
}

class Helicopter extends Vehicle {
    public Helicopter() {
        super.setGoAlgorithm(new GoByFlying());
    }
}

class Jet extends Vehicle {
    public Jet() {
        super.setGoAlgorithm(new GoByFlyingFast());
    }
}
```

Bây giờ, giả sử bạn muốn đổi cách di chuyển cho 2 phương tiện là xe đua đường phố và xe đua công thức 1: rất đơn giản, chỉ việc update `GoByDriving` là cả 2 xe đua đó đều được update, chả cần quan tâm đến các class `StreetRacer`, `FormulaOne`

```java
class GoByDriving implements GoAlgorithm {
    @Override
    public void go() {
        System.out.println("Now I'm driving version 2.3.");
    }
}
```

### 2.5. Configuring behavior at runtime

**Quan hệ "has-a" sẽ flexible nhiều hơn mối quan hệ "is-a" when it comes to configuring behavior at runtime.**

Trong cách dùng inheritance:

```java
class Jet extends Vehicle {
    @Override
    public void go() {
        System.out.println("Now I'm flying super fast!.");
    }
}
```

ta thấy method go của Jet bị hard code, bây giờ giả sử `Jet` di chuyển theo 3 bước đó là: đi chậm, sau đó bay nhanh, và cuối cùng lúc hạ cánh thì lại đi chậm, thì ta làm như nào? Nếu dùng `composition`, ta có thể THAY ĐỔI cách di chuyển trong lúc runtime như sau:

```java
Jet jet2 = new Jet();
jet2.setGoAlgorithm(new GoByDriving());
jet2.go();
jet2.setGoAlgorithm(new GoByFlyingFast());
jet2.go();
jet2.setGoAlgorithm(new GoByDriving());
jet2.go();
```

### 2.6. Strategy pattern là gì?

**Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it**

Strategy pattern là mẫu định nghĩa 1 tập các thuật toán có thể hoán đổi cho nhau, sau đó việc chọn thuật toán nào sẽ được quyết định lúc runtime

![strategy pattern](./figure2-3-strategy-pattern.png)

Nên dùng Strategy pattern trong những trường hợp sau:

- Đoạn code hay thay đổi cần phải tách riêng để dễ maintain
- You want to avoid muddling how you handle a task by having to split implementation code over several inherited classes (???)
- Muốn xác định/thay đổi thuật toán sử dụng lúc runtime

## Chapter 3: The Decorator and Factory Patterns

### 3.1 Ví dụ về việc mua chiếc burger

Xét ví dụ thực tế sau: giả sử bạn vào 1 quán ăn nhanh (KFC, Lotteria...):

- Bạn gọi 1 chiếc burger thịt
- 1 lát sau bạn lại thay đổi: "Bạn ơi mình muốn đổi sang 1 chiếc burger thịt với topping phô mai nhé"
- Nhân viên nhìn bạn 1 cái và nói "Oke"
- Xíu nữa, sau khi nhìn qua menu, b lại nói: "Hay là cho mình 1 chiếc burger thịt với phô mai và socola, được ko?"
- The cook stares at you and seems on the verge of saying something unpleasant! Nhưng vẫn nói "Oke"
- Bạn nhận lấy chiếc burger của mình và nói: "Liệu có thể thêm topping khoai tây được ko ạ?"
- ...

Một chiếc burger có thể trở thành burger phô mai, rồi lại thành burger phô mai socola, và rồi có thể thêm topping rất dễ dàng mà ko phải đập đi làm lại từ đầu. Chiếc burger là chính bắt buộc phải có, còn phô mai, socola hay là khoai tây thì cũng chỉ là các vật trang trí được thêm vào mà thôi (decorator)

Nếu dùng Inheritance để giải quyết thì sẽ thiết kế như sau:

- Tạo class `MeatBurger`
- Tạo các class con kế thừa từ `MeatBurger`: `CheeseMeatBurger`, `ChocolateMeatBurger`, `PotatoMeatBurger`
- Cần tạo THÊM các class con nữa kế thừa từ `MeatBurger`, tùy theo nhu cầu của khách hàng: `CheeseChocolateMeatBurger`, `CheesePotatoMeatBurger`, `CheeseChocolatePotatoMeatBurger`...
- Mệt chưa :v
- Giả sử có thêm 1 loại burger nữa là `VeggieBurger` (burger chay), cũng có các loại topping như trên, bài toán lúc này lại thêm rắc rối nếu dùng Inheritance vì cần tạo thêm các class con: `CheeseChocolateVeggieBurger`...

Dùng decorator pattern: xem code [BurgerExample](./chapter3/decorator/BurgerExample.java)

Ref:

- https://refactoring.guru/design-patterns/decorator
- https://viblo.asia/p/hieu-biet-co-ban-ve-decorator-pattern-pVYRPjbVG4ng

### 3.2. Decorator pattern

It's all about extending the functionality of a given class: sau khi bạn code xong 1 class, bạn có thể decor cho nó bằng cách **using wrapper code to extend your core code**, hay nó cách khác là wrap nó trong 1 class khác. Do đó Decorator pattern còn được gọi là Wrapper pattern

Doing so means that you won't have to keep modifying the original class's code over and over again

Đây chính là nguyên lý **Open/closed principle** trong **SOLID**

### 3.1. Factory pattern: ví dụ về multiple database

Theo [GPCoder](https://gpcoder.com/4352-huong-dan-java-design-pattern-factory-method/):

- Factory Pattern đúng nghĩa là một nhà máy, và nhà máy này sẽ "sản xuất" các đối tượng theo yêu cầu của chúng ta.
- Factory Pattern được sử dụng khi có một class cha (super-class) với nhiều class con (sub-class), dựa trên đầu vào và phải trả về 1 trong những class con đó

Giả sử bạn cần connect tới 3 loại database trong 1 dự án, thì có thể dùng factory pattern để tạo object Connection:

```java
package dp_for_dummies.chapter3.factory;

abstract class Connection {
    public Connection() {}

    public String description() {
        return "Generic";
    }
}

class MySqlConnection extends Connection {
    public MySqlConnection() {}

    @Override
    public String description() {
        return "MySQL";
    }
}
class OracleConnection extends Connection {} // Similar to MySqlConnection
class SqlServerConnection extends Connection {} // Similar to MySqlConnection

// A factory class is a factory class, and that’s it. It’s not designed to be extended.
// Trong sách đặt tên là "FirstFactory"
final class ConnectionFactory {
    protected String type;

    public ConnectionFactory(String t) {
        type = t;
    }

    public Connection createConnection() {
        if (type.equals("Oracle")) {
            return new OracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SqlServerConnection();
        } else {
            return new MySqlConnection();
        }
    }
}

public class FactoryPattern {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory("SQL Server");
        Connection conn = factory.createConnection();
        System.out.println(conn.description());
    }
}
```

Theo GoF, Factory pattern nên: **Define an interface for creating an object, but let subclasses decide which class to instantiate.**: tức là bạn chỉ cần định nghĩa 1 interface Factory, và **để các subclass tự implement 1 factory cụ thể**

Quay lại ví dụ trên, giả sử bây giờ, với mỗi 1 database, bạn cần có thêm 1 cách kết nối `secure` nữa, chẳng hạn:

```java
class SecureMySqlConnection extends Connection {
    public SecureMySqlConnection() {}

    @Override
    public String description() {
        return "MySQL secure";
    }
}
class SecureOracleConnection extends Connection {}
class SecureSqlServerConnection extends Connection {}
```

Ta cần định nghĩa 1 abstract factory như sau, factory này sẽ có 1 method để tạo connection. Sau đó tạo các factory cụ thể để subclass (user) chọn, hoặc cũng có thể để user tự tạo factory

```java
abstract class ConnectionFactory {
    public ConnectionFactory() {}

    protected abstract Connection createConnection(String type);
}
class NormalFactory extends ConnectionFactory {
    @Override
    public Connection createConnection(String type) {
        if (type.equals("Oracle")) {
            return new OracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SqlServerConnection();
        } else {
            return new MySqlConnection();
        }
    }
}
class SecureFactory extends ConnectionFactory {
    @Override
    public Connection createConnection(String type) {
        if (type.equals("Oracle")) {
            return new SecureOracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SecureSqlServerConnection();
        } else {
            return new SecureMySqlConnection();
        }
    }
}
```

Giờ chỉ việc xài thôi:

```java
// tự chọn 1 factory theo ý muốn
ConnectionFactory factory1 = new SecureFactory();

// sau đó yêu cầu factory tạo object connection theo ý muốn
Connection conn1 = factory1.createConnection("SQL Server");

System.out.println(conn1.description());

ConnectionFactory factory2 = new NormalFactory();
Connection conn2 = factory2.createConnection("Oracle");
System.out.println(conn2.description());
```

## Chapter 4: Observer and Chain of Responsibility Patterns

### 4.1. Observer pattern vs Chain of Responsibility Pattern

Sếp của bạn muốn nhận tất cả thông báo về bất kỳ thay đổi nào tới database, bạn liền nghĩ tới observer pattern

> You smile to yourself as you turn to the code, wondering how happy the boss is going to be with about 200,000 notifications a day

Observer pattern còn được gọi là Event-Subscriber, Listener. Nó hoạt động giống như mô hình Pub-sub đó (khá quen thuộc): Observer pattern cho phép các observer (subscriber, listener) nhận thông báo mỗi khi 1 object nào đó thay đổi. Mỗi 1 observer sẽ register (subscribe, follow, listen) tới 1 object Publisher, và khi Publisher thay đổi, mọi observer sẽ được thông báo **đồng thời**

Chain of Responsibility Pattern: khá giống với Observer pattern, chỉ khác là các observer được kết nối thành 1 chuỗi (chain). Thông báo sẽ đi từ observer này sang observer khác (tức là 1 observer xử lý thông báo xong có thể pass hoặc ko pass tới observer tiếp theo)

Theo GoF, Observer pattern should **Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically**

Các observer sẽ có thể subscribe/unsubscribe 1 Publisher tại lúc runtime, hoạt động như sau:

![figure4-1](./figure4-1.png)

![figure4-2](./figure4-2.png)

![figure4-3](./figure4-3.png)

![figure4-4](./figure4-4.png)

![figure4-5](./figure4-5.png)

### 4.2. Implement observer pattern to send notification to your boss

Đầu tiên cần 2 interface là Publisher và Observer

```java
// Trong sách đặt là Subject
public interface Publisher {
    // Thêm mới 1 object observer (có thể gọi là subscriber, người quan sát, người theo dõi),
    // Việc lưu 1 subscriber mới ở đâu sẽ để class con tự implement (Array, List, Hash...)
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    // Thông báo cho tất cả các observer đã thêm ở trên,
    // việc thông báo cái gì sẽ để class con tự implement
    public void notifyObservers();
}

// Observer giống như Subscriber vậy
public interface Observer {
    // Nhận thông báo từ Publisher:
    // Method này sẽ được bên Publisher gọi mỗi khi Publisher có thay đổi gì mới
    // Việc gọi khi nào sẽ do class con của Publisher implement
    public void update(String operation, String record);
}
```

Sửa code truy cập tới database: ta cần implement `Publisher` ở trên:

```java
// Class giúp thao tác tới database
class DatabaseHelper implements Publisher {

    private List<Observer> observers;
    private String operation;
    private String record;

    public DatabaseHelper() {
        // dùng LinkedList vì chủ yếu dùng các thao tác add, remove, duyệt tuần tự,
        // chứ ko cần truy cập phần tử ngẫu nhiên
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(operation, record);
        }
    }

    // Save record xuống database, sau khi thực hiện thì sẽ notify tới các observers
    public void saveToDb(String operation, String record) {
        this.operation = operation;
        this.record = record;
        notifyObservers();
    }
}
```

Phía class `Boss`, chỉ cần implement interface `Observer` là sẽ nhận được thông báo mỗi khi database được update

```java
// Các anh dev cần nhận được thông báo về các thay đổi của database
class Developer implements Observer {
    @Override
    public void update(String operation, String record) {
        System.out.printf("Dev đã thấy được database thay đổi, hành động '%s' trên bản ghi '%s'\n",
                operation, record);
    }
}

// Ông sếp rảnh quá nên cũng muốn nhận thông báo về các thay đổi của database
class Boss implements Observer {
    @Override
    public void update(String operation, String record) {
        System.out.printf(
                "Boss cũng có thể thấy được database thay đổi, hành động '%s' trên bản ghi '%s'\n",
                operation, record);
    }

}
```

[Full code: check this folder](./chapter4/observer/ob1_simple_example/)

### 4.3. Loose coupling

> The Observer and Chain of Responsibility design patterns implement what’s called **loose coupling**

> The design insight here is that **loose coupling between objects**, _rather than simply extending objects_ by making them do more than they were meant to do

> Go for loose coupling when it comes to information flow

### 4.4. Critical issue when passing by reference

Giả sử `record` ko phải kiểu String (immutable) nữa, mà là 1 kiểu Object, chẳng hạn Employee:

```java
public class Employee {
    private int id;
    private String name;
    // Getters, setters, constructor
    @Override
    public String toString() { return "Employee{id:'" + id + "',name:'" + name + "'}"; }
}
public interface Observer {
    // record kiểu Object chứ ko phải String nữa
    public void update(String operation, Object record);
}
class DatabaseHelper implements Publisher {
    private List<Observer> observers;
    private String operation;
    private Employee employee; // record kiểu Employee chứ ko phải String nữa

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            // Bởi vì Java pass theo reference, do đó bên Observer
            // HOÀN TOÀN CÓ THỂ THAY ĐỔI giá trị bên trong Employee
            o.update(operation, employee);
        }
    }

    public void saveToDb(String operation, Employee employee) {
        this.operation = operation;
        this.employee = employee;
        notifyObservers();
    }
}
```

Bởi vì Java pass theo reference, do đó bên Observer HOÀN TOÀN CÓ THỂ THAY ĐỔI giá trị bên trong Employee:

```java
// Các anh dev cần nhận được thông báo về các thay đổi của database
class Developer implements Observer {
    @Override
    public void update(String operation, Object record) {
        System.out.printf(
                "Dev đã thấy được database thay đổi:\n\thành động '%s' trên bản ghi '%s'\n",
                operation, record);

        // This name has been alter by an observer!!! This is a critical issue that should be fixed
        if (record instanceof Employee) {
            ((Employee) record).setName("Hahaha!");
        }
    }
}
```

[Full code: check this folder](./chapter4/observer/ob2_pass_reference/)

Kết quả run thử:

```
Dev đã thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Lorem ipsum'}'
Leader đã thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Hahaha!'}'
Boss cũng có thể thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Hahaha!'}'
```

Ta thấy sau khi Dev thay đổi record, Leader và Boss ko còn nhận được thông báo đúng nữa! (Tên của employee bị thay đổi 'Lorem ipsum' -> 'Hahaha', leader và boss ko hề biết)

### 4.5. Using Java built-in Observer

Giải pháp: có thể dùng Publisher và Observer có sẵn của Java, nhưng hiện tại đã bị lỗi thời:

```java
import java.util.Observable;
@SuppressWarnings("deprecation")
class DatabaseHelper extends Observable {
    // ...
    public void saveToDb(String operation, Employee employee) {
        this.setOperation(operation);
        this.setEmployee(employee);
        setChanged();   // phải gọi method này của Observable trước khi notify
        notifyObservers();  // notify sự thay đổi cho các observer biết
    }
}

import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")
class Developer implements Observer {
    @Override
    public void update(Observable obs, Object arg) {
        System.out.printf(
                "Dev đã thấy được database thay đổi:\n\thành động '%s' trên bản ghi '%s'\n",
                ((DatabaseHelper) obs).getOperation(),
                ((DatabaseHelper) obs).getEmployee().getName());

        // Thay đổi data ở đây ko ảnh hưởng tới các observer khác
        ((DatabaseHelper) obs).getEmployee().setName("Hahaha");
    }
}
```

[Full code: check this folder](./chapter4/observer/ob3_pass_reference_using_java_observer/)

Kết quả run: 1 observer thay đổi data sẽ ko ảnh hưởng tới các observer khác

```
Dev đã thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Lorem ipsum'}'
Leader đã thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Lorem ipsum'}'
Boss cũng có thể thấy được database thay đổi:
	hành động 'INSERT' trên bản ghi 'Employee{id:'1',name:'Lorem ipsum'}'
```

Tuy nhiên, Observer có sẵn của Java đã bị deprecated, do đó đây ko hẳn là 1 solution hay ho lắm!

TODO: tìm 1 giải pháp khác!!!

### 4.6. Chain of Responsibility Pattern

Khá giống với Observer pattern, khác ở chỗ pattern này có nhiều observer được chain (nối) với nhau giống 1 DSLK, và request đầu tiên sẽ được gửi tới observer1, nếu nó ko xử lý được thì sẽ pass xuống observer2...

![figure-4-7](./figure4-7.png)

Ví dụ: giả sử hệ thống của bạn có 3 tầng như sau:

![figure-4-8](./figure4-8.png)

User sẽ gửi request nhờ xử lý, đầu tiên sẽ để tầng Frontend handle đã, nếu ko handle được thì tầng FE sẽ gửi request đó xuống tầng 2, nếu tầng 2 vẫn ko xử lý được thì sẽ gửi xuống tầng dưới cùng là tầng Application để xử lý

```java
public interface HelpInterface {
    public void getHelp(HelpEnum helpEnum);
}

class FrontEnd implements HelpInterface {
    // Biến này chính là observer tiếp theo, nếu observer này ko xử lý được
    // thì sẽ pass request xuống observer đó
    private HelpInterface successor;
    public FrontEnd(HelpInterface successor) {
        this.successor = successor;
    }
    @Override
    public void getHelp(HelpEnum helpEnum) {
        if (helpEnum != HelpEnum.FRONT_END_HELP) {
            successor.getHelp(helpEnum);
        } else {
            System.out.println("This is the front end. Don’t you like it?");
        }
    }
}
class IntermediateLayer implements HelpInterface {
    private HelpInterface successor;    // observer tiếp theo
    public IntermediateLayer(HelpInterface successor) {
        this.successor = successor;
    }
    @Override
    public void getHelp(HelpEnum helpEnum) {
        if (helpEnum != HelpEnum.INTERMEDIATE_LAYER_HELP) {
            successor.getHelp(helpEnum);
        } else {
            System.out.println("This is the intermediate layer. Nice, eh?");
        }
    }
}
class Application implements HelpInterface {
    public Application() {}
    @Override
    public void getHelp(HelpEnum helpEnum) {
        System.out.println(
                "This is final help layer, if we cannot help you, try to find help from somewhere else!");
    }
}
```

## Chapter 5: From One to Many: The Singleton and Flyweight Patterns

### 5.1. Singleton pattern

Đây là pattern đơn giản nhất rồi nên chả cần nói nhiều: Singleton đảm bảo 1 class chỉ có duy nhất 1 instance, và cung cấp 1 method để có thể truy cập vào instance đó

Có nhiều cách implement Singleton (bao gồm cả thread safe), [xem thêm tại đây](../design_pattern/creational/singleton/readme.md)

Note: việc implement singleton dùng `synchronized method` khá là expensive

### 5.2. The Flyweight Pattern Makes One Look like Many

Ngoài Singleton, Flyweight Pattern cũng có thể hạn chế việc tạo mới object, nhưng pattern này sẽ cho bạn cảm giác là đang dùng nhiều object dù thực chất bạn chỉ đang dùng 1 object 🤨

**A flyweight is a shared object that can be used in multiple contexts simultaneously**

Bất cứ khi nào bạn có một số lượng lớn các object rất lớn, có thể nghĩ tới Flyweight pattern. Pattern này hoạt động giống như 1 template vậy

Giả sử có 1 class Student như sau:

```java
class Student {
    private String name;
    private int id;
    private int score;
    private double averageScore;    // điểm trung bình của cả lớp

    // getters, setters

    public double getStanding() {
        return ((score) / averageScore - 1.0) * 100.0;
    }
}
```

Trong đó field `averageScore` là giống nhau nhau với từng học sinh. Ta sẽ tạo duy nhất 1 object Student có averageScore cố định trong suốt chương trình

```java
public static void main(String[] args) {
    String names[] = {"Ralph", "Alice", "Sam"};
    int ids[] = {1001, 1002, 1003};
    int scores[] = {45, 55, 65};

    double total = Arrays.stream(scores).reduce(0, (a, b) -> a + b);
    double averageScore = total / scores.length;
    Student student = new Student(averageScore);    // chỉ tạo 1 object Student

    for (int i = 0; i < scores.length; i++) {
        student.setName(names[i]);
        student.setId(ids[i]);
        student.setScore(scores[i]);
        System.out.printf("Name: %s, with standing: %.0f%%\n", student.getName(),
                student.getStanding());
    }
}
```

## Chapter 6: The Adapter and Facade Patterns

### 6.1. Adapter pattern

The Adapter design pattern lets you fix the interface between objects and classes without having to modify the objects or classes directly

Giả sử hệ thống của bạn có 2 phần: UI và BE. Flow của hệ thống là UI gửi object `Ace` cho BE xử lý. Và BE cũng chỉ nhận object `Ace` mà thôi

![figure6-1](./figure6-1.png)

Một ngày đẹp trời, sếp bạn nói muốn BE chuyển sang dùng object mới là `Acme`, khi này phía UI vẫn gửi object `Ace`, còn BE lúc này chỉ nhận `Acme` thôi 😑

![figure6-2](./figure6-2.png)

Solution: tạo 1 adapter để chuyển đổi từ `Ace` (gửi từ UI) sang `Acme` (cho BE xài)

![figure6-3](./figure6-3.png)

```java
// Phía UI chỉ gửi cho BE object kiểu Ace, và hiện tại BE cũng chỉ handle object kiểu Ace
interface Ace {
    public void setName(String n);
    public String getName();
}

// New update: phía BE lúc này muốn chuyển sang dùng object Acme, tức là nó
// chỉ có thể nhận Acme object để xử lý thôi!
interface Acme {
    public void setFirstName(String f);
    public void setLastName(String l);
    public String getFirstName();
    public String getLastName();
}

// How to solve: tạo 1 adapter để chuyển đổi object Ace được gửi từ phía FE
// sang object Acme mà BE có thể handle được.
// => Adapt Ace object để nó trông giống như Acme object
class AceToAcmeAdapter implements Acme {
    // adapter cần chứa 1 object Ace (object cần được chuyển đổi, cần được adapt (adapted object))
    Ace ace;
    String firstName;
    String lastName;

    public AceToAcmeAdapter(AceClass ace) {
        this.ace = ace;
        firstName = ace.getName().split(" ")[0];
        lastName = ace.getName().substring(firstName.length()).trim();
    }

    @Override
    public void setFirstName(String f) {
        this.firstName = f;
    }
    @Override
    public void setLastName(String l) {
        this.lastName = l;
    }
    @Override
    public String getFirstName() {
        return firstName;
    }
    @Override
    public String getLastName() {
        return lastName;
    }
}

public static void main(String[] args) {
    // Ace object được gửi từ UI
    AceClass ace = new AceClass();
    ace.setName("Tony Stark");

    // Phía BE: Acme object được chuyển đổi từ Ace object
    Acme acme = new AceToAcmeAdapter(ace);
    System.out.println(acme.getFirstName() + ", " + acme.getLastName());
}
```

> Adapter converts the interface of a class into another interface the client expects.

Bạn sử dụng Adapter khi cố gắng lắp một chốt hình vuông vào một lỗ hình tròn

> An adapter uses composition to store the object it’s supposed to adapt, and when the adapter’s methods are called, it translates those calls into something the adapted object can understand and passes the calls on to the adapted object

### 6.2. Simplifying Life with Facades

The Adapter pattern adapts code to work with other code, while the Facade pattern gives you a wrapper that makes the original code easier to deal with

Giả sử bạn làm việc với 1 thư viện có chức năng print, bạn phải gọi lần lượt 1 đống các method sau để có thể in 1 đoạn text:

```java
interface Printer {
    void initPrinter();
    void turnFanOn();
    void warmUp();
    void getData(String text);
    void formatData(String text);
    void checkPaperSupply();
    void runInternalDiagnostics();
    void print(String text);
    void cleanUp();
}
```

Quá nhiều method! Bạn liền tạo 1 class tên là FacadePrinter, trong này bạn sẽ gọi toàn bộ đống method ở trên

```java
class FacadePrinter {
    void print(String text) {
        initPrinter();
        turnFanOn();
        warmUp();
        getData(text);
        formatData(text);
        checkPaperSupply();
        runInternalDiagnostics();
        print(text);
        cleanUp();
    }
}
```

> Provide a unified interface to a set of interfaces in a system. Facade defines a higher-level interface that makes the subsystem easier to use

Facade đơn giản hóa 1 interface

![figure6-7](./figure6-7.png)

The Adapters are more about making an interface easier to work with, while Facade defines a higher-level interface that makes the subsystem easier to use

Warning: dùng Facade phải tạo thêm 1 layer, nếu các code bên dưới thay đổi, thì bạn phải update cả layer Facade

## Chapter 7: The Template Method and Builder Patterns

### 7.1. Template Method pattern

Giả sử công ty bạn muốn lập trình 1 con robot để nó có thể tự thiết kế xe hơi. Cái con robot xe hơi này có các method chính sau

```java
// Automotive: thuộc về oto
class AutomotiveRobot {
    public void start() {
        System.out.println("Starting....");
    }
    public void getParts() {
        System.out.println("Getting a carburetor...."); // Bộ chế hòa khí, 1 bộ phận của oto
    }
    public void assemble() {
        System.out.println("Installing the carburetor....");
    }
    public void test() {
        System.out.println("Revving the engine....");
    }
    public void stop() {
        System.out.println("Stopping....");
    }
    public void go() {
        start();
        getParts();
        assemble();
        test();
        stop();
    }
}
```

Con robot thiết kế xong, chạy ngon lành! Sau đó tháng sau, sếp bạn lại muốn sản xuất thêm 1 loại robot nữa để nó sản xuất bánh quy! Con robot này có các bước làm bánh quy giống với robot oto, chỉ khác ở vài step, nếu như thiết kế nó từ đầu thì duplicate code khá nhiều!

Bạn liền nghĩ đến Template method pattern!

Nó là cái quái gì? Template method xác định khung (skeleton) của một thuật toán, trì hoãn (ko implement chi tiết) một số bước đối với các lớp con. Pattern này cho phép các lớp con override lại các bước nhất định của một thuật toán mà không thay đổi cấu trúc của thuật toán

Nghĩa là: bạn dùng Template method pattern khi bạn có 1 thuật toán gồm nhiều bước, và bạn muốn customize lại 1 số bước trong đó! (bước nào dùng luôn được thì khỏi customize lại)

Note: nếu bạn phải customize toàn bộ các step, thì Template method pattern ko cần thiết!

```java
abstract class RobotTemplate {
    public void start() {
        System.out.println("Starting....");
    }
    public void getParts() {
        System.out.println("Getting parts....");
    }
    public void assemble() {
        System.out.println("Assembling....");
    }
    public void test() {
        System.out.println("Testing....");
    }
    public void stop() {
        System.out.println("Stopping....");
    }

    // thuật toán go của template gồm các step như ở dưới (theo đúng trình tự và số lượng),
    // class con có thể customize 1 vài step, và tất nhiên nó ko thê được phép
    // customize method này
    public final void go() {
        start();
        getParts();
        assemble();
        test();
        stop();
    }
}


// Robot oto cần định nghĩa lại các step: getParts, assemble, test
class AutomotiveRobot extends RobotTemplate { // Automotive: thuộc về oto
    @Override
    public void getParts() {
        System.out.println("Getting a carburetor...."); // Bộ chế hòa khí, 1 bộ phận của oto
    }
    @Override
    public void assemble() {
        System.out.println("Installing the carburetor....");
    }
    @Override
    public void test() {
        System.out.println("Revving the engine....");
    }
}

// Robot bánh quy cũng cần định nghĩa lại 3 step sau
class CookieRobot extends RobotTemplate {
    @Override
    public void getParts() {
        System.out.println("Getting flour and sugar....");
    }
    @Override
    public void assemble() {
        System.out.println("Baking a cookie....");
    }
    @Override
    public void test() {
        System.out.println("Crunching a cookie....");
    }
}
```

### 7.2. Add hook

> A hook is a method that controls some aspect of that algorithm

Giả sử bạn muốn tạo mới Robot làm bánh chưng và bạn muốn skip 1 step thì làm như nào? Có thể thêm hook như sau:

```java
abstract class RobotTemplate {
    // Mặc định thuật toán go sẽ cần step test, nếu class con nào ko cần
    // thì chỉ cần override lại method này
    public boolean isTest() {
        return true;
    }
    public final void go() {
        start();
        getParts();
        assemble();
        if (isTest()) { // make the testing part optional
            test();
        }
        stop();
    }
}

class ChungCakeRobot extends RobotTemplate {
    @Override
    public void getParts() {
        System.out.println("Chuẩn bị lá dong, nạt tre, gạo nếp, thịt ba chỉ, đậu xanh....");
    }

    @Override
    public void assemble() {
        System.out.println("Gói bánh xong ngồi đun....");
    }

    @Override
    public boolean isTest() {
        return false;
    }
}
```

> You use the Template Method design pattern when you’ve got an algorithm of several steps and you want to allow customization by subclasses
