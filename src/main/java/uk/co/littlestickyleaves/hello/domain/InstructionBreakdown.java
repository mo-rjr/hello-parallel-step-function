package uk.co.littlestickyleaves.hello.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class InstructionBreakdown {

    private Map<String, String> run = new HashMap<>();

    private List<String> urls = new ArrayList<>();

    public InstructionBreakdown() {
    }
    public InstructionBreakdown(String id, List<String> urls) {
        this.run = Map.of("id", id);
        this.urls = urls;
    }

    public InstructionBreakdown(Map<String, String> run, List<String> urls) {
        this.run = run;
        this.urls = urls;
    }

    public void setId(String id) {
        run.put("id", id);
    }

    public Map<String, String> getRun() {
        return run;
    }

    public void setRun(Map<String, String> run) {
        this.run = run;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
