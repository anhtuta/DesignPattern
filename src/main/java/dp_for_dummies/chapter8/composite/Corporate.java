package dp_for_dummies.chapter8.composite;

import java.util.Iterator;

abstract class Corporate {

    // bắt buộc các class con đều phải định nghĩa các print
    public abstract void print();

    public abstract String getName();

    public abstract Iterator<? extends Corporate> iterator();

    // mặc định ko có method add (chẳng hạn VP ko cần add gì)
    public void add(Corporate c) {}

}

