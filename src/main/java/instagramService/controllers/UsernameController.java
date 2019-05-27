package instagramService.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.scraper.InstagramScraper;
import instagramService.user.JsonBuilder;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Map;


@RestController
public class UsernameController {

    private final InstagramScraper scraper;
    private final JsonBuilder jsonBuilder;

    @Inject
    UsernameController(final InstagramScraper scrape, final JsonBuilder builder){
        this.scraper = scrape;
        this.jsonBuilder = builder;
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public Map<String, Object> username(@RequestParam String username) {
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(username));
            final JsonNode userJson = scraper.extractUserJson(userPage);
            return jsonBuilder.buildJsonResponse(userJson);
        } catch (Exception e) {
            return jsonBuilder.buildFailedResponse();
        }
    }



    //Multiple users through post? or multiple get parameters? Definitely not a GET. Would have to be a POST that takes a JSON of usernames;

}
