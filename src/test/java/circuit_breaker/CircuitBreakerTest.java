package circuit_breaker;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Ref: Copilot
 */
class CircuitBreakerTest {
    @Test
    void testSuccessCallKeepsClosed() throws Exception {
        CircuitBreaker cb = new CircuitBreaker(2, 1000);
        Callable<String> success = () -> "OK";
        assertEquals("OK", cb.invoke(success));
        assertEquals(CircuitBreaker.BreakerState.CLOSED, cb.state);
    }

    @Test
    void testFailureOpensBreaker() {
        CircuitBreaker cb = new CircuitBreaker(1, 1000);
        Callable<String> fail = () -> { throw new ServiceUnavailableException("fail"); };
        assertThrows(Exception.class, () -> cb.invoke(fail));
        assertEquals(CircuitBreaker.BreakerState.OPEN, cb.state);
    }

    @Test
    void testAllowRequestBlocksWhenOpen() {
        CircuitBreaker cb = new CircuitBreaker(1, 1000);
        Callable<String> fail = () -> { throw new ServiceUnavailableException("fail"); };
        try { cb.invoke(fail); } catch (Exception ignored) {}
        assertThrows(Exception.class, () -> cb.invoke(() -> "OK"));
    }

    @Test
    void testHalfClosedState() throws Exception {
        CircuitBreaker cb = new CircuitBreaker(1, 500); // 1 failure, short reset
        Callable<String> fail = () -> { throw new ServiceUnavailableException("fail"); };
        Callable<String> success = () -> "OK";
        // Cause breaker to open
        assertThrows(Exception.class, () -> cb.invoke(fail));
        assertEquals(CircuitBreaker.BreakerState.OPEN, cb.state);
        // Wait for resetMillis to pass
        Thread.sleep(600);
        // Try a call, should be HALF_CLOSED
        try {
            cb.invoke(success);
        } catch (Exception ignored) {}
        assertEquals(CircuitBreaker.BreakerState.CLOSED, cb.state);
    }
}
