package design_pattern.creational.abstract_factory.refactoringguru.furniture_shop;

// ===== 3. Abstract Factory =====
public abstract class FurnitureFactory {

    // Factory methods to create Products
    protected abstract Chair createChair();
    protected abstract Sofa createSofa();
    protected abstract CoffeeTable createCoffeeTable();

}

// ===== 4. Concrete Factories =====
// ArtDecoFactory only create ArtDeco family product
class ArtDecoFactory extends FurnitureFactory {
    @Override
    protected Chair createChair() {
        return new ArtDecoChair();
    }

    @Override
    protected Sofa createSofa() {
        return new ArtDecoSofa();
    }

    @Override
    protected CoffeeTable createCoffeeTable() {
        return new ArtDecoCoffeeTable();
    }
}

class VictorianFactory extends FurnitureFactory {
    @Override
    protected Chair createChair() {
        return new VictorianChair();
    }

    @Override
    protected Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    protected CoffeeTable createCoffeeTable() {
        return new VictorianCoffeeTable();
    }
}

class ModernFactory extends FurnitureFactory {
    @Override
    protected Chair createChair() {
        return new ModernChair();
    }

    @Override
    protected Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    protected CoffeeTable createCoffeeTable() {
        return new ModernCoffeeTable();
    }
}
