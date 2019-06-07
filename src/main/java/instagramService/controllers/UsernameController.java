package instagramService.controllers;

import com.google.common.collect.ImmutableMap;
import instagramService.scraper.ScraperThread;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@RestController
public class UsernameController {

    private final ScraperThread scraperThread;

    @Inject
    UsernameController(final ScraperThread thread){
      this.scraperThread = thread;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List< Map<String, Map<String, Object>>> getUsernames(@RequestParam String[] usernames) {
      return getScrapedUsernamesJson(usernames);
    }

    //TODO
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List< Map<String, Map<String, Object>>> postUsernames(@RequestBody String[] usernames) {
        return getScrapedUsernamesJson(usernames);
    }


    private  List< Map<String, Map<String, Object>>> getScrapedUsernamesJson(final String[] usernames){
        //Create a thread for each Instagram page to scrape.
        final ExecutorService executorService = Executors.newWorkStealingPool();
        List< Map<String, Map<String, Object>>> result = new ArrayList<>();
        try {
            for(String username : usernames){
                executorService.submit(()-> result.add(scraperThread.scrape(username)));
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            result.add(executorServiceError());
        }
        return result;
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
