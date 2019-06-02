package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.user.JsonBuilder;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Map;

@Component
public class ScraperThread {

    private final InstagramScraper scraper;
    private final JsonBuilder jsonBuilder;

    @Inject
    ScraperThread(final InstagramScraper scrape, final JsonBuilder builder){
        this.scraper = scrape;
        this.jsonBuilder = builder;
    }

    public Map<String, Map<String, Object>> scrape(final String username){
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(username));
            final JsonNode userJson = scraper.extractUserJson(userPage);
            return jsonBuilder.buildJsonResponse(userJson, username);
        } catch (Exception e) {
            return jsonBuilder.buildFailedResponse(username);
        }
    }

}
