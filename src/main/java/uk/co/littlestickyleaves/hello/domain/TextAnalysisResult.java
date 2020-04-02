package uk.co.littlestickyleaves.hello.domain;

import java.util.Map;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
@Deprecated
public class TextAnalysisResult {

    private Map<String, Long> frequencies;

    public TextAnalysisResult() {
    }

    public TextAnalysisResult(Map<String, Long> frequencies) {
        this.frequencies = frequencies;
    }

    public Map<String, Long> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Map<String, Long> frequencies) {
        this.frequencies = frequencies;
    }
}
