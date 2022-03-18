package dp_for_dummies.chapter4.observer.ob3_pass_reference_using_java_observer;

import java.util.Observable;

@SuppressWarnings("deprecation")
class DatabaseHelper extends Observable {

    private String operation;
    private Employee employee;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void saveToDb(String operation, Employee employee) {
        this.setOperation(operation);
        this.setEmployee(employee);
        setChanged();
        notifyObservers();
    }

}

