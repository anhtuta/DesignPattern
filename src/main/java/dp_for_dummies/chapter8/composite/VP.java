package dp_for_dummies.chapter8.composite;

import java.util.Iterator;

class VP extends Corporate {
    private String name;
    private String division;

    public VP(String name, String division) {
        super();
        this.name = name;
        this.division = division;
    }

    @Override
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

    // Do VP ko lưu collection nào nên ko cần Iterator
    @Override
    public Iterator<VP> iterator() {
        return new VPIterator();
    }

    private class VPIterator implements Iterator<VP> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public VP next() {
            return null;
        }
    }

    @Override
    public void print() {
        System.out.println("[VP] Name: " + name + ", Division:" + division);
    }
}

