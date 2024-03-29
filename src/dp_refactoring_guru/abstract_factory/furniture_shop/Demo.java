package dp_refactoring_guru.abstract_factory.furniture_shop;

public class Demo {

    public static void main(String[] args) {
        FurnitureFactory factory1 = new ArtDecoFactory();
        App app1 = new App(factory1);
        app1.testFurniture();

        System.out.println();
        FurnitureFactory factory2 = new VictorianFactory();
        App app2 = new App(factory2);
        app2.testFurniture();

        System.out.println();
        FurnitureFactory factory3 = new ModernFactory();
        App app3 = new App(factory3);
        app3.testFurniture();
    }

}
