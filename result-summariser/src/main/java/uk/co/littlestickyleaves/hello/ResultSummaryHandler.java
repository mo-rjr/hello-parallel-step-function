package uk.co.littlestickyleaves.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.SystemOutput;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisResult;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class ResultSummaryHandler implements RequestHandler<TextAnalysisResult, SystemOutput> {

    @Override
    public SystemOutput handleRequest(TextAnalysisResult textAnalysisResult, Context context) {
        String result = "Letter frequencies are : " + frequencyMapAsString(textAnalysisResult.getFrequencies());
        context.getLogger().log(result);
        return new SystemOutput(result);
    }

    private String frequencyMapAsString(Map<String, Long> frequencies) {
        return frequencies.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
