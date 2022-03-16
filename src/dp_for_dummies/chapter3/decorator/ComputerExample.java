package dp_for_dummies.chapter3.decorator;

class Computer {
    public Computer() {}

    public String description() {
        return "computer";
    }
}


// Thông thường ComponentDecorator sẽ kế thừa từ 1 abstraction, tức là cần tạo thêm 1 interface
// IComputer, sau đó Computer implement IComputer, rồi tạo ComponentDecorator implement IComputer
// Còn nữa, có thể để biến Computer computer; ở class cha này
// (Xem ví dụ về burger)
abstract class ComponentDecorator extends Computer {
    @Override
    public abstract String description();
}


class Disk extends ComponentDecorator {
    Computer computer;

    public Disk(Computer c) {
        computer = c;
    }

    @Override
    public String description() {
        return computer.description() + " and a disk";
    }
}


class CD extends ComponentDecorator {
    Computer computer;

    public CD(Computer c) {
        computer = c;
    }

    @Override
    public String description() {
        return computer.description() + " and a CD";
    }
}


class Monitor extends ComponentDecorator {
    Computer computer;

    public Monitor(Computer c) {
        computer = c;
    }

    @Override
    public String description() {
        return computer.description() + " and a monitor";
    }
}


public class ComputerExample {

    public static void main(String[] args) {
        Computer computer = new Computer();
        System.out.println(computer.description());

        computer = new Disk(computer);
        System.out.println(computer.description());

        computer = new CD(computer);
        System.out.println("You're getting a " + computer.description());
    }
}
