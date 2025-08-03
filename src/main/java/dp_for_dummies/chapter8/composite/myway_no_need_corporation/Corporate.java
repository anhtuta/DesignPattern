package dp_for_dummies.chapter8.composite.myway_no_need_corporation;

import java.util.Iterator;

// Abstract class dùng cho cả Division và VP
abstract class Corporate {

    // bắt buộc các class con đều phải định nghĩa các print
    // param level: level của Division hoặc VP trong tập đoàn
    public abstract void print(int level);

    public abstract String getName();

    public abstract Iterator<? extends Corporate> iterator();

    // mặc định ko có method add (chẳng hạn VP ko cần add gì)
    public void add(Corporate c) {}

    // Với mỗi level, lúc print sẽ thêm số tab = số level ở trước
    public String getTotalTabs(int level) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < level; i++) {
            builder.append("\t");
        }
        return builder.toString();
    }
}

