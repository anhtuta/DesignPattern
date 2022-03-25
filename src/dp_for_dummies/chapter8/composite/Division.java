package dp_for_dummies.chapter8.composite;

import java.util.Iterator;

class Division extends Corporate {
    // Do bên trong 1 Division có cả Division khác và Vice President,
    // mà 2 object đó đều là con của Corporate, do đó có thể lưu cả 2
    // trong 1 array kiểu Corporate
    private Corporate[] corporate = new Corporate[100];
    private int number = 0;
    private String name;

    public Division(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Tên là add chứ ko phải là addVP như trước, vì giờ có thể
    // phải add cả 1 object kiểu Division
    @Override
    public void add(Corporate c) {
        corporate[number++] = c;
    }

    @Override
    public Iterator<Corporate> iterator() {
        return new DivisionIterator();
    }

    @Override
    public void print() {
        System.out.println("Division: " + name);
        Iterator<Corporate> iterator = iterator();
        while (iterator.hasNext()) {
            Corporate c = iterator.next();
            c.print();
        }
    }

    private class DivisionIterator implements Iterator<Corporate> {
        private int location = 0;

        @Override
        public boolean hasNext() {
            return location < corporate.length && corporate[location] != null;
        }

        @Override
        public Corporate next() {
            return corporate[location++];
        }
    }
}
