package design_pattern.creational.dependency_injection.ex2;

public class Test {

    public static void main(String[] args) {
        // DI
        HairStyle hairStyle1 = new Sidepart();
        Outfit outfit1 = new Coolmate();
        Shoe shoe1 = new Converse();
        
        User u1 = new User(hairStyle1, outfit1, shoe1);
        System.out.println(u1.getInfo());
        
        // Dùng IOC để tạo và inject các dependency khi cần
        HairStyle hairStyle2 = (HairStyle) BeanContextDemo.getBean("sidepart");
        Outfit outfit2 = (Outfit) BeanContextDemo.getBean("louisVuitton");
        Shoe shoe2 = (Shoe) BeanContextDemo.getBean("converse");

        User u2 = new User(hairStyle2, outfit2, shoe2);
        System.out.println(u2.getInfo());
    }
}
