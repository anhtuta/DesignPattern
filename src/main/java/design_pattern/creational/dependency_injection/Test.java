package design_pattern.creational.dependency_injection;

public class Test {

    public static void main(String[] args) {
        UserController u1 = new UserController(new EmailService());
        u1.sendMessage("Hehehe");
        UserController u2 = new UserController(new FacebookService());
        u2.sendMessage("Hahaha");
    }
}
