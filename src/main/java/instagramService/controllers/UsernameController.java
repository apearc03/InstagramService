package instagramService.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.scraper.JsonResponseBuilder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;


@RestController
public class UsernameController {

    private final JsonResponseBuilder jsonResponseBuilder;

    @Inject
    UsernameController(final JsonResponseBuilder responseBuilder){
      this.jsonResponseBuilder = responseBuilder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<JsonNode> getUsernames(@RequestParam String[] usernames) {
        return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<JsonNode> postUsernames(@RequestBody String[] usernames) {
        return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }
}
