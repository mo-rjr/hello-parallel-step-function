package uk.co.littlestickyleaves.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.Instruction;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisTask;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class InstructionReceiverHandler implements RequestHandler<Instruction, TextAnalysisTask> {

    public TextAnalysisTask handleRequest(Instruction instruction, Context context) {
        return new TextAnalysisTask(instruction.getTestUrls().get(0));
    }
}
