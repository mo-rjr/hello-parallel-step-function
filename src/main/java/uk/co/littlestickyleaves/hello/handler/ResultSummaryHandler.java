package uk.co.littlestickyleaves.hello.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.SummaryInput;
import uk.co.littlestickyleaves.hello.domain.SystemOutput;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Takes in a number of TextAnalysisResults and summarises them
 */
public class ResultSummaryHandler implements RequestHandler<SummaryInput, SystemOutput> {

    @Override
    public SystemOutput handleRequest(SummaryInput summaryInput, Context context) {
        String id = summaryInput.getId();
        List<Map<String, Long>> textAnalysisResults = summaryInput.getResults();
        Map<String, Long> combinedFrequencies = textAnalysisResults.stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)));
        String result = id + ": Combined letter frequencies from " + textAnalysisResults.size() + " input" +
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
