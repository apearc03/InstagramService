package instagramService.controllers;

import instagramService.scraper.JsonResponseBuilder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@RestController
public class UsernameController {

    private final JsonResponseBuilder jsonResponseBuilder;

    @Inject
    UsernameController(final JsonResponseBuilder responseBuilder){
      this.jsonResponseBuilder = responseBuilder;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List< Map<String, Map<String, Object>>> getUsernames(@RequestParam String[] usernames) {
      return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List< Map<String, Map<String, Object>>> postUsernames(@RequestBody String[] usernames) {
        return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }

}
