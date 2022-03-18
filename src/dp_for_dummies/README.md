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

