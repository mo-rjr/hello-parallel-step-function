package uk.co.littlestickyleaves.hello.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.Instruction;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisTask;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Receives an Instruction with a list of URLs
 * Outputs an array of instructions
 */
public class InstructionHandler implements RequestHandler<Instruction, List<TextAnalysisTask>> {

    @Override
    public List<TextAnalysisTask> handleRequest(Instruction instruction, Context context) {
        return instruction.getTextUrls().stream()
                .map(TextAnalysisTask::new)
                .collect(Collectors.toList());
    }
}
