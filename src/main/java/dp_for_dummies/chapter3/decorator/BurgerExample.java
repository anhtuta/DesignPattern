package dp_for_dummies.chapter3.decorator;

// 1. Component: Common interface for both wrappers and wrapped objects (object sẽ được wrap)
interface Burger {
    String makeBurger();
}


// 2. Concrete Component: chính là class của các object sẽ được wrap
class MeatBurger implements Burger {
    @Override
    public String makeBurger() {
        return "Making a meat burger";
    }
}


// 2. Concrete Component
class VeggieBurger implements Burger {
    @Override
    public String makeBurger() {
        return "Making a veggie burger";
    }
}


// 3. Base Decorator: class này sẽ có 1 field tham chiếu tới object bị wrap
// Cụ thể, class này sẽ giữ 1 object Burger tồn tại trước đó,
// và object burger này sẽ được mở rộng trong lúc runtime
abstract class BurgerDecorator implements Burger {
    protected Burger burger;

    public BurgerDecorator(Burger burger) {
        this.burger = burger;
    }
}


// 4. Concrete Decorator: xác định các hành vi bổ sung có thể được
// thêm 1 cách dynamically vào các thành phần
// Cụ thể: Các object của BurgerDecorator sẽ gọi method makeBurger()
// của object gốc và add something to it
class CheeseDecorator extends BurgerDecorator {
    public CheeseDecorator(Burger burger) {
        super(burger);
    }

    @Override
    public String makeBurger() {
        return burger.makeBurger() + " and Cheese";
    }
}


class ChocolateDecorator extends BurgerDecorator {
    public ChocolateDecorator(Burger burger) {
        super(burger);
    }

    @Override
    public String makeBurger() {
        return burger.makeBurger() + " and Chocolate";
    }
}


class PotatoDecorator extends BurgerDecorator {
    public PotatoDecorator(Burger burger) {
        super(burger);
    }

    @Override
    public String makeBurger() {
        return burger.makeBurger() + " and Potato";
    }
}


public class BurgerExample {
    public static void main(String[] args) {
        Burger burger1 = new MeatBurger();
        System.out.println(burger1.makeBurger());

        // Thêm topping phô mai
        burger1 = new CheeseDecorator(burger1);
        System.out.println(burger1.makeBurger());

        // Thêm topping socola
        burger1 = new ChocolateDecorator(burger1);
        System.out.println(burger1.makeBurger());

        // Thêm topping khoai tây
        burger1 = new PotatoDecorator(burger1);
        System.out.println(burger1.makeBurger());

        Burger burger2 = new VeggieBurger();
        burger2 = new ChocolateDecorator(burger2);
        burger2 = new CheeseDecorator(burger2);
        System.out.println(burger2.makeBurger());
    }
}
