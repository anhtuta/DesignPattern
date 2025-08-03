package dp_for_dummies.chapter8.iterator.myway_using_inner_class;

import java.util.Iterator;

// Vice President
class VP {
    private String name; // tên của VP
    private String division; // tên của division mà ông ta đang quản lý

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
// Cty bạn có quá nhiều phó chủ tịch!!! Thật nực cười :v
class Division {
    private String name; // tên bộ phận
    private VP[] VPs = new VP[100];
    private int number = 0; // số lượng VP, chỉ dùng cho CTDL Division, tức là ko có getter

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
        return new VPIterator();
    }

    // Thường thì sẽ tạo class Iterator là inner class, để có thể access trực tiếp tới
    // biến mà CTDL dùng để lưu data (trong vd này là mảng VPs của Division)
    // (trong sách tên là DivisionIterator)
    private class VPIterator implements Iterator<VP> {
        private int location = 0;

        @Override
        public boolean hasNext() {
            return location < VPs.length && VPs[location] != null;
        }

        @Override
        public VP next() {
            return VPs[location++];
        }
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
