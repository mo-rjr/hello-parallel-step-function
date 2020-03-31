package uk.co.littlestickyleaves.hello.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class Instruction {

    private List<String> textUrls = new ArrayList<>();

    public Instruction() {
    }

    public Instruction(List<String> textUrls) {
        this.textUrls = textUrls;
    }

    public void addUrl(String url) {
        textUrls.add(url);
    }

    public void setTextUrls(List<String> textUrls) {
        this.textUrls = textUrls;
    }

    public List<String> getTextUrls() {
        return textUrls;
    }
}
