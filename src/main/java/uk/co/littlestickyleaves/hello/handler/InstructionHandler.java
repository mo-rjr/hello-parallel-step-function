package uk.co.littlestickyleaves.hello.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.Instruction;
import uk.co.littlestickyleaves.hello.domain.InstructionBreakdown;

import java.util.List;
import java.util.UUID;

/**
 * Receives an Instruction with a list of URLs
 * Outputs an Id with a list of URLS
 */
public class InstructionHandler implements RequestHandler<Instruction, InstructionBreakdown> {

    @Override
    public InstructionBreakdown handleRequest(Instruction instruction, Context context) {
        String id = UUID.randomUUID().toString();
        List<String> textUrls = instruction.getTextUrls();
        context.getLogger().log("Received request of size " + textUrls.size() + ": assigning id " + id);
        return new InstructionBreakdown(id, textUrls);
    }
}
