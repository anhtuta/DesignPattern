package dp_for_dummies.chapter4.observer.ob2_pass_reference;

import java.util.LinkedList;
import java.util.List;

public interface Publisher {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObservers();

}


class DatabaseHelper implements Publisher {

    private List<Observer> observers;
    private String operation;
    private Employee employee;

    public DatabaseHelper() {
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
            o.update(operation, employee);
        }
    }

    public void saveToDb(String operation, Employee employee) {
        this.operation = operation;
        this.employee = employee;
        notifyObservers();
    }

}

