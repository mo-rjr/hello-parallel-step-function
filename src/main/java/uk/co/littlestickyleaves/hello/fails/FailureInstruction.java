package uk.co.littlestickyleaves.hello.fails;

/**
 * POJO to hold desired failure type
 */
public class FailureInstruction {

    private FailureType failureType;


    public FailureType getFailureType() {
        return failureType;
    }

    public void setFailureType(FailureType failureType) {
        this.failureType = failureType;
    }

    @Override
    public String toString() {
        return "FailureInstruction{" +
                "failureType=" + failureType +
                '}';
    }
}
