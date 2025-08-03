package circuit_breaker;

import java.util.concurrent.Callable;

public class App {
    public static void main(String[] args) {
        CircuitBreaker cb = new CircuitBreaker(2, 2000); // 2 failures, 2s reset
        Callable<String> successCall = () -> "Service Success";
        Callable<String> failCall = () -> { throw new ServiceUnavailableException("Service Down"); };

        // Test CLOSED -> OPEN
        System.out.println("--- Test CLOSED to OPEN ---");
        for (int i = 0; i < 2; i++) {
            try {
                cb.invoke(failCall);
            } catch (Exception e) {
                System.out.println("Call " + i + ": Exception - " + e.getClass().getSimpleName());
            }
            System.out.println("State after call " + i + ": " + cb.state);
        }

        // Test OPEN blocks requests
        System.out.println("\n--- Test OPEN blocks ---");
        try {
            cb.invoke(successCall);
        } catch (Exception e) {
            System.out.println("Blocked call in OPEN state: " + e.getClass().getSimpleName());
        }
        System.out.println("State: " + cb.state);

        // Wait for resetMillis to pass for HALF_CLOSED
        System.out.println("\n--- Waiting for HALF_CLOSED ---");
        try { Thread.sleep(2100); } catch (InterruptedException ignored) {}
        try {
            cb.invoke(successCall);
            System.out.println("Call in HALF_CLOSED: Success");
        } catch (Exception e) {
            System.out.println("Call in HALF_CLOSED: Exception - " + e.getClass().getSimpleName());
        }
        System.out.println("State: " + cb.state);
    }
}
