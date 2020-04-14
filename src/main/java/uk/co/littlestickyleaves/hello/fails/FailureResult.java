package uk.co.littlestickyleaves.hello.fails;

/**
 * POJO for output of handler
 */
public class FailureResult {

    private boolean successful;

    public FailureResult() {
        this.successful = true;
    }

    public FailureResult(boolean successful) {
        this.successful = successful;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }
}
