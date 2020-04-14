package uk.co.littlestickyleaves.hello.fails;

/**
 * A custom exception which can be caught and retried
 */
public class RetryableException extends RuntimeException {

    public RetryableException(String message) {
        super(message);
    }
}
