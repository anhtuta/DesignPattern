package design_pattern.creational.dependency_injection.ex2;

public class User {

    private HairStyle hairStyle;
    private Outfit outfit;
    private Shoe shoe;

    public HairStyle getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(HairStyle hairStyle) {
        this.hairStyle = hairStyle;
    }

    public Outfit getOutfit() {
        return outfit;
    }

    public void setOutfit(Outfit outfit) {
        this.outfit = outfit;
    }

    public Shoe getShoe() {
        return shoe;
    }

    public void setShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public User(HairStyle hairStyle, Outfit outfit, Shoe shoe) {
        super();
        this.hairStyle = hairStyle;
        this.outfit = outfit;
        this.shoe = shoe;
    }

    public String getInfo() {
        return "Hair: " + hairStyle.getHairStyle() + ", Outfit: " + outfit.getOutfit() + ", Shoe: " + shoe.getShoe();
    }
}
