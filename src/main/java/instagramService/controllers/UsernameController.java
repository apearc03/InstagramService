package instagramService.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.scraper.InstagramScraper;
import instagramService.scraper.ScraperThreadDispatcher;
import instagramService.user.JsonBuilder;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
public class UsernameController {

    private final ScraperThreadDispatcher dispatcher;

    @Inject
    UsernameController(final ScraperThreadDispatcher threadDispatcher){
      this.dispatcher = threadDispatcher;
    }


    /*@RequestMapping(value = "/username", method = RequestMethod.GET)
    public Map<String, Object> username(@RequestParam String username) {
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(username));
            final JsonNode userJson = scraper.extractUserJson(userPage);
            return jsonBuilder.buildJsonResponse(userJson);
        } catch (Exception e) {
            return jsonBuilder.buildFailedResponse();
        }
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, List<Map<String, Object>>> username(@RequestParam String[] usernames) {
        Map<String, List<Map<String,Object>>> response = new HashMap<>();
        response.put("users",
                Stream.of(usernames)
                        .map(username -> dispatcher.scrape(username))
                        .collect(Collectors.toList())
        );
        return response;
    }


    //Multiple users through post? or multiple get parameters? Definitely not a GET. Would have to be a POST that takes a JSON of usernames;

}
