package uk.co.littlestickyleaves.hello;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisResult;
import uk.co.littlestickyleaves.hello.domain.TextAnalysisTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * {A thing} to {do something} for {another thing}
 * -- for example, {this}
 * -- and also {this}
 */
// TODO fill in Javadoc
public class TextAnalysisHandler implements RequestHandler<TextAnalysisTask, TextAnalysisResult> {

    private static HttpClient HTTP_CLIENT = null;

    @Override
    public TextAnalysisResult handleRequest(TextAnalysisTask textAnalysisTask, Context context) {
        LambdaLogger logger = context.getLogger();
        setUpClientIfRequired(logger);

        try (InputStream inputStream = getUrlAsInputStream(textAnalysisTask.getUrl());
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            Map<String, Long> frequenciesOfLetters = bufferedReader.lines()
                    .map(line -> line.replaceAll("[^a-zA-Z]", ""))
                    .map(String::toLowerCase)
                    .flatMap(line -> Arrays.stream(line.split("")))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
            return new TextAnalysisResult(frequenciesOfLetters);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private InputStream getUrlAsInputStream(String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
            HttpResponse<InputStream> response = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() > 300) {
                throw new RuntimeException("Request to " + url + " failed with status code " + response.statusCode());
            }
            return response.body();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void setUpClientIfRequired(LambdaLogger logger) {
        if (HTTP_CLIENT == null) {
            logger.log("Setting up http client");
            HTTP_CLIENT = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_2)
                    .connectTimeout(Duration.ofSeconds(20))
                    .build();
        } else {
            logger.log("Http client already set up -- hot lambda!");
        }
    }
}
