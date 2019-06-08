package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class JsonResponseBuilder {

    private final InstagramScraper scraper;
    private final JsonBuilder jsonBuilder;

    @Inject
    JsonResponseBuilder(final InstagramScraper scrape, final JsonBuilder builder){
        this.scraper = scrape;
        this.jsonBuilder = builder;
    }


    public List< Map<String, Map<String, Object>>> getScrapedUsernamesJson(final String[] usernames){
        //Create a thread for each Instagram page to scrape.
        final ExecutorService executorService = Executors.newWorkStealingPool();
        List< Map<String, Map<String, Object>>> result = new ArrayList<>();
        try {
            for(String username : usernames){
                executorService.submit(()-> result.add(scrape(username)));
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            result.add(executorServiceError());
        }
        return result;
    }


    private Map<String, Map<String, Object>> scrape(final String username){
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(username));
            final JsonNode userJson = scraper.extractUserJson(userPage);
            return jsonBuilder.buildJsonResponse(userJson, username);
        } catch (Exception e) {
            return jsonBuilder.buildFailedResponse(username);
        }
    }



    private static Map<String, Map<String, Object>> executorServiceError(){
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put("Error",
                        ImmutableMap.<String, Object>builder()
                                .put("ErrorMessage", "There was an error processing your request")
                                .build())
                .build();
    }

}
