package uk.co.littlestickyleaves.hello.fails;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Random;

/**
 * A handler which can be directed how to fail,
 * including a RANDOM option
 */
public class RandomFailingHandler implements RequestHandler<FailureInstruction, FailureResult> {

    private static final Random RANDOM = new Random();

    @Override
    public FailureResult handleRequest(FailureInstruction failureInstruction, Context context) {
        context.getLogger().log("Received " + failureInstruction);

        switch(failureInstruction.getFailureType()) {
            case CUSTOM:
                custom();
            case RUNTIME:
                runtime();
            case TIMEOUT:
                timeout();
            case RANDOM:
                randomFailure(context.getLogger());
            case NONE:
            default:
                // do nothing;
        }

        return new FailureResult();
    }

    private void randomFailure(LambdaLogger logger) {
        int randomInt = RANDOM.nextInt(9);

        switch(randomInt) {
            case 0:
            case 1:
                logger.log("Random number " + randomInt + " means custom error");
                custom();
            case 2:
            case 3:
                logger.log("Random number " + randomInt + " means runtime error");
                runtime();
            case 4:
            case 5:
            case 6:
            case 7:
                logger.log("Random number " + randomInt + " means timeout error");
                timeout();
            default:
                logger.log("Random number " + randomInt + " means success");
                // do nothing
        }

    }

    private void custom() {
        throw new RetryableException("Worth retrying -- a custom exception");
    }

    private void runtime() {
        throw new RuntimeException("This is a runtime exception");
    }

    private void timeout() {
        try {
            Thread.sleep(60 * 60 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Here!
            throw new RuntimeException(e);
        }
    }
}
