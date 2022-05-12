package design_pattern.creational.abstract_factory.refactoringguru.furniture_shop;

public interface Sofa {
    String getColor();
    String layDown();
}

class ArtDecoSofa implements Sofa {
    @Override
    public String getColor() {
        return "Blue";
    }

    @Override
    public String layDown() {
        return "Wow, this one is perfect for sleeping!";
    }
}

class VictorianSofa implements Sofa {
    @Override
    public String getColor() {
        return "Yellow";
    }

    @Override
    public String layDown() {
        return "This one is good for taking a short nap!";
    }
}

class ModernSofa implements Sofa {
    @Override
    public String getColor() {
        return "White";
    }

    @Override
    public String layDown() {
        return "Hmm, I cannot sleep on this sofa!";
    }
}

