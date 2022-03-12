package dp_for_dummies.chapter2.c2_2_composition;

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
// custom lại
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

public class StartTheRace {
    public static void main(String[] args) {
        StreetRacer streetRacer = new StreetRacer();
        FormulaOne formulaOne = new FormulaOne();
        Helicopter helicopter = new Helicopter();
        Jet jet = new Jet();

        streetRacer.go();
        formulaOne.go();
        helicopter.go();
        jet.go();

        Jet jet2 = new Jet();
        jet2.setGoAlgorithm(new GoByDriving());
        jet2.go();
        jet2.setGoAlgorithm(new GoByFlyingFast());
        jet2.go();
        jet2.setGoAlgorithm(new GoByDriving());
        jet2.go();
    }
}
