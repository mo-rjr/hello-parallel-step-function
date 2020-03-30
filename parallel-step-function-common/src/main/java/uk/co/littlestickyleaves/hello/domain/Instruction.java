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

    private List<String> testUrls = new ArrayList<>();

    public Instruction() {
    }

    public Instruction(List<String> testUrls) {
        this.testUrls = testUrls;
    }

    public void addUrl(String url) {
        testUrls.add(url);
    }

    public void setTestUrls(List<String> testUrls) {
        this.testUrls = testUrls;
    }

    public List<String> getTestUrls() {
        return testUrls;
    }
}
