package instagramService.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.scraper.JsonResponseBuilder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class UsernameController {

    private final JsonResponseBuilder jsonResponseBuilder;

    @Inject
    UsernameController(final JsonResponseBuilder responseBuilder){
      this.jsonResponseBuilder = responseBuilder;
    }

    @RequestMapping(value = "/usernames", method = RequestMethod.GET)
    public List<JsonNode> getUsernames(@RequestParam String[] usernames) {
        return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }

    @RequestMapping(value = "/usernames", method = RequestMethod.POST)
    public List<JsonNode> postUsernames(@RequestBody String[] usernames) {
        return jsonResponseBuilder.getScrapedUsernamesJson(usernames);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void rootToSwaggerRedirect(final HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
}
