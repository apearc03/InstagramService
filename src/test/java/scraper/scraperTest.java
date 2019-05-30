package scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import instagramService.config.Config;
import instagramService.config.ConfigModule;
import instagramService.scraper.InstagramScraper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class scraperTest {


    private Config config;
    private ObjectMapper mapper;
    private InstagramScraper scraper;
    private ConfigModule configModule;

    @Before
    public void setup(){
        configModule = new ConfigModule();
        config = configModule.provideConfig();
        mapper = new ObjectMapper();
        scraper = new InstagramScraper(config, mapper);
    }

    @Test
    public void testExtraction(){
        final String testUser = "davidbeckham";
        try {
            Document d = scraper.getDocument(scraper.buildUrl(testUser));
            Element e = d.body();
            Elements bodyelements = e.children();
            Element userElement = bodyelements
                    .stream()
                    .filter(element -> element.data().contains("window._sharedData") && element.data().contains("user")
                    )
                    .findFirst()
                    .orElseThrow(IOException::new);
            String data = userElement.data();
            String json = data.substring(data.indexOf("{"));
            ObjectMapper mapper = new ObjectMapper();
            JsonNode actualObj = mapper.readTree(json);
            JsonNode s = actualObj.get("entry_data").get("ProfilePage").get(0).get("graphql").get("user");
            System.out.println();
            //TODO clean this up and put into a method. Actually create test.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
