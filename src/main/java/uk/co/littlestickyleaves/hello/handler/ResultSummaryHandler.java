package uk.co.littlestickyleaves.hello.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.SystemOutput;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisResult;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Takes in a number of TextAnalysisResults and summarises them
 */
public class ResultSummaryHandler implements RequestHandler<List<TextAnalysisResult>, SystemOutput> {

    @Override
    public SystemOutput handleRequest(List<TextAnalysisResult> textAnalysisResults, Context context) {
        Map<String, Long> combinedFrequencies = textAnalysisResults.stream()
                .map(TextAnalysisResult::getFrequencies)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));
        String result = "Combined letter frequencies from " + textAnalysisResults.size() + " input" +
                (textAnalysisResults.size() == 1 ? " " : "s ")  + "are: " +
                frequencyMapAsString(combinedFrequencies);
        context.getLogger().log(result + "\n");
        return new SystemOutput(result);
    }

    private String frequencyMapAsString(Map<String, Long> frequencies) {
        return frequencies.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(", "));
    }
}
