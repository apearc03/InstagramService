package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.userInfo.UserParent;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Component
public class JsonResponseBuilder {

    private final InstagramScraper scraper;
    private final JsonUserInfoBuilder jsonBuilder;

    @Inject
    JsonResponseBuilder(final InstagramScraper scrape, final JsonUserInfoBuilder builder){
        this.scraper = scrape;
        this.jsonBuilder = builder;
    }


    public List<UserParent> getScrapedUsernamesJson(final String[] usernames){
        //Create a thread for each Instagram page to scrape.
        final ExecutorService executorService = Executors.newWorkStealingPool();
        final List<UserParent> result = new ArrayList<>();
        try {
            for(final String username : usernames){
                executorService.submit(()-> result.add(scrape(username)));
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (final Exception e) {
            return result;
        }
        return result;
    }

    private UserParent scrape(final String username){
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(username));
            final JsonNode userJson = scraper.extractUserJson(userPage);
            return jsonBuilder.buildUserJson(userJson, username);
        } catch (Exception e) {
            return jsonBuilder.buildFailedUserJson(username);
        }
    }

}
