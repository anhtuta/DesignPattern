package design_pattern.creational.abstract_factory.refactoringguru.furniture_shop;

public class App {

    private Chair chair;
    private Sofa sofa;
    private CoffeeTable coffeeTable;

    public App(FurnitureFactory factory) {
        chair = factory.createChair();
        sofa = factory.createSofa();
        coffeeTable = factory.createCoffeeTable();
    }

    public void testFurniture() {
        System.out.println("Welcome to our furniture shop! We have lots of thing for you!");
        System.out.println("First, let's take a look at our chair");
        if (chair.hasLegs()) {
            System.out.println("Our chair has 4 legs");
        } else {
            System.out.println("Our chair doesn't have any legs");
        }
        System.out.println("Let try to sit. " + chair.sitOn());
        System.out.println("Next is the Sofa. Its color is " + sofa.getColor());
        System.out.println("How about sleeping on this? " + sofa.layDown());
        System.out.println(
                "And finally out last product, a coffee table. It's a/an " + coffeeTable.getName());
        System.out.println("Do you want a drink with this table? " + coffeeTable.letDrink());
    }


}
