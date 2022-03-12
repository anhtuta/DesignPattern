package dp_for_dummies.chapter2.c2_1_inheritance;

abstract class Vehicle {
    public void go() {
        System.out.println("Now I'm driving.");
    }
}


class StreetRacer extends Vehicle {
}


class FormulaOne extends Vehicle {
}


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

        streetRacer.go();
        formulaOne.go();
        helicopter.go();
        jet.go();
    }
}
