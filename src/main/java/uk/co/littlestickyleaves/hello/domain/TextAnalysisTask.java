package uk.co.littlestickyleaves.hello.domain;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
@Deprecated
public class TextAnalysisTask {

    private String url;

    public TextAnalysisTask() {
    }

    public TextAnalysisTask(String url) {
        this.url = url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
