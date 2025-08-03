package dp_refactoring_guru.abstract_factory.furniture_shop;

// ===== 1. Abstract Product =====
public interface Chair {
    boolean hasLegs();
    String sitOn();
}

// ===== 2. Concrete Products =====
class ArtDecoChair implements Chair {
    @Override
    public boolean hasLegs() {
        return false;
    }

    @Override
    public String sitOn() {
        return "We're sitting on an ArtDeco chair, it's so comfortable!";
    }
}

class VictorianChair implements Chair {
    @Override
    public boolean hasLegs() {
        return true;
    }

    @Override
    public String sitOn() {
        return "We're sitting on a Victorian chair, it looks so luxurious!";
    }
}

class ModernChair implements Chair {
    @Override
    public boolean hasLegs() {
        return false;
    }

    @Override
    public String sitOn() {
        return "We're sitting on a Modern chair, it's suck!";
    }
}
