package dp_for_dummies.chapter8.composite.myway_no_need_corporation;

public class CompositePattern {

    public static void main(String[] args) {
        Division corporation = new Division("Công ty cổ phần Tuzaku");

        Division rnd = new Division("R&D");
        rnd.add(new VP("Steve", "R&D"));
        rnd.add(new VP("Mike", "R&D"));
        rnd.add(new VP("Nancy", "R&D"));

        Division sales = new Division("Sales");
        sales.add(new VP("Ted", "Sales"));
        sales.add(new VP("Bob", "Sales"));
        sales.add(new VP("Carol", "Sales"));
        sales.add(new VP("Alice", "Sales"));

        Division western = new Division("Western Sales");
        western.add(new VP("Wally", "Western Sales"));
        western.add(new VP("Andre", "Western Sales"));
        sales.add(western);

        VP vp = new VP("Cary", "Division root");

        corporation.add(rnd); // add sub-division
        corporation.add(sales); // add sub-division
        corporation.add(vp); // add VP

        corporation.print(0);
    }
}
