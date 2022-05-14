package dp_refactoring_guru.abstract_factory.furniture_shop;

// ===== 1. Abstract Product =====
public interface CoffeeTable {
    String getName();
    String letDrink();
}

// ===== 2. Concrete Products =====
class ArtDecoCoffeeTable implements CoffeeTable {
    @Override
    public String getName() {
        return "ArtDeco coffee table";
    }

    @Override
    public String letDrink() {
        return "Let's drink black cafe, shall we?";
    }
}

class VictorianCoffeeTable implements CoffeeTable {
    @Override
    public String getName() {
        return "Victorian coffee table";
    }

    @Override
    public String letDrink() {
        return "Let's drink Mojito, shall we?";
    }
}

class ModernCoffeeTable implements CoffeeTable {
    @Override
    public String getName() {
        return "Modern coffee table";
    }

    @Override
    public String letDrink() {
        return "I don't want to drink anything next to this table!";
    }
}
