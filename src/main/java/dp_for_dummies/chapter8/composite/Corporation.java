package dp_for_dummies.chapter8.composite;

import java.util.ArrayList;
import java.util.Iterator;

// Class Corporation dùng để add các object kiểu Corporate
class Corporation extends Corporate {
    private ArrayList<Corporate> corporate = new ArrayList<Corporate>();

    @Override
    public void add(Corporate c) {
        corporate.add(c);
    }

    @Override
    public void print() {
        Iterator<Corporate> iterator = corporate.iterator();
        while (iterator.hasNext()) {
            Corporate c = iterator.next();
            c.print();
        }
    }

    // Riêng class này ko cần name và iterator
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Iterator<? extends Corporate> iterator() {
        return null;
    }
}

