package circuit_breaker;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Circuit Breaker (CB) là một mẫu thiết kế được sử dụng để ngăn chặn các lỗi từ các dịch vụ bên ngoài
 * làm ảnh hưởng đến ứng dụng của bạn. Khi một dịch vụ bên ngoài gặp sự cố, CB sẽ chuyển sang trạng thái
 * OPEN và từ chối các yêu cầu mới cho đến khi dịch vụ được phục hồi.
 *
 * Lớp này mô phỏng hành vi của Circuit Breaker với các trạng thái CLOSED, OPEN và HALF_CLOSED.
 * Khi CB ở trạng thái CLOSED, nó cho phép các yêu cầu đi qua. Nếu có quá nhiều lỗi (được xác định
 * bởi một bộ đếm lỗi), CB sẽ chuyển sang trạng thái OPEN, từ chối các yêu cầu mới và chỉ
 * cho phép các yêu cầu sau một khoảng thời gian nhất định (được xác định bởi resetMillis) để
 * trở lại trạng thái HALF_CLOSED.
 *
 * Khi CB ở trạng thái HALF_CLOSED, nó sẽ cho phép một số yêu cầu đi qua để kiểm tra xem dịch vụ
 * đã phục hồi hay chưa. Nếu các yêu cầu này thành công, CB sẽ chuyển lại trạng thái CLOSED.
 */
public class CircuitBreaker {
    // trạng thái hiện tại của CircuitBreaker (CB)
    protected volatile BreakerState state;

    // bộ đếm lỗi (fail counter) của CB, cứ quá số lần lỗi là CB OPEN ngay.
    protected AtomicInteger numberFailure;

    // thời điểm lỗi cuối cùng, dùng để tính toán timeout để tự động chuyển trạng thái từ sang HALF_OPEN
    protected AtomicLong lastFailure;

    // thời gian timeout của CB
    protected AtomicLong resetMillis;

    // số lần lỗi tối đa trước khi chúng ta OPEN CB
    protected int limit;

    public CircuitBreaker(int limit, long resetMillis) {
        this.state = BreakerState.CLOSED;
        this.lastFailure = new AtomicLong(0L);
        this.numberFailure = new AtomicInteger(0);
        this.resetMillis = new AtomicLong(resetMillis);
        this.limit = limit;
    }

    /**
     * Linh hồn của Circuit Breake đó là hàm invoke, ý tưởng ở đây là tạo ra một hàm với đầu vào là một Callable
     * và đầu ra (return) sẽ tuỳ vào việc ứng dụng trả ra cái gì, ứng dụng sẽ gọi đến hàm invoke của CB và
     * truyền vào hàm gọi đến external services bên ngoài
     *
     * @param c Callable<V> - hàm gọi đến external services bên ngoài
     * @return V - kết quả trả về từ hàm gọi đến external services bên ngoài
     */
    public <V> V invoke(Callable<V> c) throws Exception {
        if (!this.allowRequest()) {
            throw new Exception();
        } else {
            try {
                V result = c.call();
                this.close();
                return result;
            } catch (Throwable var) {
                this.handleFailure(var);
                throw new IllegalStateException("not possible");
            }
        }
    }

    /**
     * Lọc các Exeption hợp lệ để tăng bộ đếm lỗi, nếu bộ đếm đã tới limit thì mở trạng thái OPEN
     * còn không thì trạng thái CB vẫn tiếp tục là CLOSED
     */
    private void handleFailure(Throwable cause) {
        if (cause instanceof ServiceUnavailableException &&
                numberFailure.compareAndSet(this.limit, numberFailure.incrementAndGet())) {
            this.open();
        } else {
            this.close();
        }
    }

    private boolean allowRequest() {
        if (BreakerState.CLOSED == this.state) {
            return true;
        } else {
            // check nếu CB đang trong trạng thái OPEN mà timeout đã hết thì chuyển trạng thái CB sang HALF_OPEN
            if (BreakerState.OPEN == this.state &&
                    System.currentTimeMillis() - this.lastFailure.get() >= this.resetMillis.get()) {
                this.state = BreakerState.HALF_CLOSED;
                return true;
            }
        }
        return false;
    }

    public void open() {
        this.state = BreakerState.OPEN;
        this.lastFailure.set(System.currentTimeMillis());
        this.numberFailure = new AtomicInteger(0);
    }

    private void close() {
        this.state = BreakerState.CLOSED;
    }

    public enum BreakerState {
        OPEN,
        HALF_CLOSED,
        CLOSED
    }
}
