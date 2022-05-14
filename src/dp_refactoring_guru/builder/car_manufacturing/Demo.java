package dp_refactoring_guru.builder.car_manufacturing;

public class Demo {

    public static void main(String[] args) {
        Director director = new Director();
        Car car = director.constructSportsCar();
        System.out.println("Car built:\n" + car.print());
    }

}
