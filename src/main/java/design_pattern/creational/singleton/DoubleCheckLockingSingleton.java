package design_pattern.creational.singleton;

/*
 * Kiểm tra sự tồn tại thể hiện của lớp, với sự hổ trợ của đồng bộ hóa, hai lần trước khi khởi tạo.
 * Cách này giúp tránh việc đồng bộ hóa không cần thiết sau khi thể hiện đã được khởi tạo.
 * Nói đơn giản hơn: check hai lần instance == null
 * - Lần đầu tiên kiểm tra trước khi đồng bộ hóa để tránh việc đồng bộ hóa không cần thiết.
 * - Lần thứ hai kiểm tra bên trong khối synchronized để đảm bảo rằng không có thread nào khác
 * đã tạo thể hiện trong khi thread hiện tại đang chờ đồng bộ hóa.
 *
 * Note: Phải khai báo volatile cho instance để tránh lớp làm việc không chính xác do quá trình
 * tối ưu hóa của trình biên dịch. Cụ thể:
 * - Giả dụ lúc này instance chưa hề được khởi tạo và có một Thread A và đi qua double checked locking
 * và nó sẽ khởi tạo Object instance vào bộ nhớ riêng L1, nhưng chưa thực sự hoàn thành và
 * context switch đột nhiên xuất hiện.
 * - Đúng lúc này Thread B xuất hiện và cũng cần sử dụng instance, và nó cũng qua double checked locking
 * và phát hiện ra rằng Object instance đã được khởi tạo ở bước trước ở bộ nhớ riêng nhờ Cache coherence protocols,
 * lúc này Thread B sẽ tưởng rằng Object instance đã thực sự tồn tại nó sẽ lấy ra và trả về dữ liệu,
 * lúc này chương trình sẽ bị crash vì thực sự Object instance vẫn chưa thực sự hoàn thành việc khởi tạo
 */
public class DoubleCheckLockingSingleton {
    private static volatile DoubleCheckLockingSingleton instance;

    private DoubleCheckLockingSingleton() {
    }

    public static DoubleCheckLockingSingleton getInstance() {
        // Do something before get instance ...
        if (instance == null) {
            // Do the task too long before create instance ...
            // Block so other threads cannot come into while initialize
            synchronized (DoubleCheckLockingSingleton.class) {
                // Re-check again. Maybe another thread has initialized before
                if (instance == null) {
                    instance = new DoubleCheckLockingSingleton();
                }
            }
        }
        // Do something after get instance ...
        return instance;
    }
}
