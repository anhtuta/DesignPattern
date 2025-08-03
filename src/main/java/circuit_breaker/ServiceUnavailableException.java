package circuit_breaker;

/**
 * ở bên ngoài ứng dụng mỗi khi gặp một lỗi hợp lệ muốn kích hoạt CB thì ta chỉ cần throw ra lớp này là được.
 */
public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String message) {
        super(message);
    }

    public ServiceUnavailableException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
