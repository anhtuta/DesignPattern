package dp_for_dummies.chapter4.observer.ob1_simple_example;

// Observer giống như Subscriber vậy
public interface Observer {
    // Nhận thông báo từ Publisher:
    // Method này sẽ được bên Publisher gọi mỗi khi Publisher có thay đổi gì mới
    // Việc gọi khi nào sẽ do class con của Publisher implement
    public void update(String operation, String record);
}


// Các anh dev cần nhận được thông báo về các thay đổi của database
class Developer implements Observer {
    @Override
    public void update(String operation, String record) {
        System.out.printf("Dev đã thấy được database thay đổi, hành động '%s' trên bản ghi '%s'\n",
                operation, record);
    }
}


// Leader cũng cần biết về các thay đổi của database
class ProjectLeader implements Observer {
    @Override
    public void update(String operation, String record) {
        System.out.printf(
                "Leader đã thấy được database thay đổi, hành động '%s' trên bản ghi '%s'\n",
                operation, record);
    }
}


// Ông sếp rảnh quá nên cũng muốn nhận thông báo về các thay đổi của database
class Boss implements Observer {

    @Override
    public void update(String operation, String record) {
        System.out.printf(
                "Boss cũng có thể thấy được database thay đổi, hành động '%s' trên bản ghi '%s'\n",
                operation, record);
    }

}
