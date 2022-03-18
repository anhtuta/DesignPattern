package dp_for_dummies.chapter4.observer.ob1_simple_example;

import java.util.LinkedList;
import java.util.List;

// Trong sách đặt là Subject
public interface Publisher {

    // Thêm mới 1 object observer (có thể gọi là subscriber, người quan sát, người theo dõi),
    // Việc lưu 1 subscriber mới ở đâu sẽ để class con tự implement (Array, List, Hash...)
    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    // Thông báo cho tất cả các observer đã thêm ở trên,
    // việc thông báo cái gì sẽ để class con tự implement
    public void notifyObservers();

}


// Class giúp thao tác tới database
class DatabaseHelper implements Publisher {

    private List<Observer> observers;
    private String operation;
    private String record;

    public DatabaseHelper() {
        // dùng LinkedList vì chủ yếu dùng các thao tác add, remove, duyệt tuần tự,
        // chứ ko cần truy cập phần tử ngẫu nhiên
        observers = new LinkedList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(operation, record);
        }
    }

    // Save record xuống database, sau khi thực hiện thì sẽ notify tới các observers
    public void saveToDb(String operation, String record) {
        this.operation = operation;
        this.record = record;
        notifyObservers();
    }

}
