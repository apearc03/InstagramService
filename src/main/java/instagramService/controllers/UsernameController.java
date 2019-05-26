package instagramService.controllers;

import instagramService.scraper.InstagramScraper;
import instagramService.user.JsonBuilder;
import instagramService.user.UserInfo;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.IOException;
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


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, Object> test(@RequestParam String name) {
        try {
            final Document userPage = scraper.getDocument(scraper.buildUrl(name));
            final UserInfo userInfo = scraper.buildUserInfo(userPage);
            return jsonBuilder.buildJsonResponse(userInfo);
        } catch (IOException e) {
            return jsonBuilder.buildFailedResponse();
        }
    }



    //Multiple users through post? or multiple get parameters? Definitely not a GET. Would have to be a POST that takes a JSON of usernames;

}
