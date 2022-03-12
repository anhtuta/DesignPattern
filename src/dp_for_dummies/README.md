## Chapter 2: Putting Plans into Action with the Strategy Pattern

Polymorphism (đa hình) thường phát huy tác dụng khi bạn làm việc với design pattern vì design pattern có xu hướng thiên về composition hơn là inheritance. (Composition (tổng hợp, kết hợp) nghĩa là khi object của bạn chứa các object khác thay vì kế thừa từ chúng.

Design pattern-oriented programming (Lập trình hướng mẫu thiết kế) thường thích composition hơn là inheritance. Khi bạn sử dụng composition, code của bạn chứa các đối tượng khác, thay vì kế thừa từ chúng.

### Bài toán thiết kế Vehicle

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

### Xử lý thay đổi với `has-a` thay vì `is-a`

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

### Tách riêng code hay thay đổi

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

### Configuring behavior at runtime

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
