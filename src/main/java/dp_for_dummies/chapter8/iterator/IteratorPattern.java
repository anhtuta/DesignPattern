package dp_for_dummies.chapter8.iterator;

import java.util.Iterator;

// Vice President
class VP {
    private String name;
    private String division;

    public VP(String name, String division) {
        super();
        this.name = name;
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Division: " + division;
    }
}


// Mỗi bộ phận lưu danh sách các phó chủ tịch!
// Cty có quá nhiều phó chủ tịch!!!
class Division {
    private String name; // tên bộ phận
    private VP[] VPs = new VP[100];
    private int number = 0;

    public Division(String name) {
        super();
        this.name = name;
    }

    public void addVP(String name) {
        VP vp = new VP(name, this.name);
        VPs[number++] = vp;
    }

    // Iterating over vice presidents:
    // iterator để duyệt các phó chủ tịch của cty
    public Iterator<VP> vpIterator() {
        return new VPIterator(VPs);
    }
}


class VPIterator implements Iterator<VP> {
    private VP[] VPs;
    private int location = 0;

    public VPIterator(VP[] vPs) {
        VPs = vPs;
    }

    @Override
    public boolean hasNext() {
        return location < VPs.length && VPs[location] != null;
    }

    @Override
    public VP next() {
        return VPs[location++];
    }
}


public class IteratorPattern {
    public static void main(String[] args) {
        Division division = new Division("Sales");
        division.addVP("Ted");
        division.addVP("Bob");
        division.addVP("Carol");
        division.addVP("Alice");

        Iterator<VP> iter = division.vpIterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
