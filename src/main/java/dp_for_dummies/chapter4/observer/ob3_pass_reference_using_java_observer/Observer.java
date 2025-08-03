package dp_for_dummies.chapter4.observer.ob3_pass_reference_using_java_observer;

import java.util.Observable;
import java.util.Observer;

// Các anh dev cần nhận được thông báo về các thay đổi của database
@SuppressWarnings("deprecation")
class Developer implements Observer {

    @Override
    public void update(Observable obs, Object arg) {
        System.out.printf(
                "Dev đã thấy được database thay đổi:\n\thành động '%s' trên bản ghi '%s'\n",
                ((DatabaseHelper) obs).getOperation(),
                ((DatabaseHelper) obs).getEmployee().getName());

        // Thay đổi data ở đây ko ảnh hưởng tới các observer khác
        ((DatabaseHelper) obs).getEmployee().setName("Hahaha");
    }
}


// Leader cũng cần biết về các thay đổi của database
@SuppressWarnings("deprecation")
class ProjectLeader implements Observer {
    @Override
    public void update(Observable obs, Object arg) {
        System.out.printf(
                "Leader đã thấy được database thay đổi:\n\thành động '%s' trên bản ghi '%s'\n",
                ((DatabaseHelper) obs).getOperation(),
                ((DatabaseHelper) obs).getEmployee().getName());
    }
}


// Ông sếp rảnh quá nên cũng muốn nhận thông báo về các thay đổi của database
@SuppressWarnings("deprecation")
class Boss implements Observer {

    @Override
    public void update(Observable obs, Object arg) {
        System.out.printf(
                "Boss cũng có thể thấy được database thay đổi:\n\thành động '%s' trên bản ghi '%s'\n",
                ((DatabaseHelper) obs).getOperation(),
                ((DatabaseHelper) obs).getEmployee().getName());
    }

}
