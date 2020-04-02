package uk.co.littlestickyleaves.hello.domain;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class SummaryInput {

    private String id;

    private Frequencies results;

    public SummaryInput() {
    }

    public SummaryInput(String id, Frequencies results) {
        this.id = id;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Frequencies getResults() {
        return results;
    }

    public void setResults(Frequencies results) {
        this.results = results;
    }
}
