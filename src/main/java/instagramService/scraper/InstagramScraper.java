package instagramService.scraper;

import instagramService.config.Config;
import instagramService.user.UserInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class InstagramScraper {

    private final Config config;

    @Inject
    InstagramScraper(final Config conf){
        this.config = conf;
    }

    public Document getDocument(final String url) throws IOException {
        return Jsoup.connect(url).get();
    }


    public UserInfo buildUserInfo(final Document d){
        return new UserInfo(
                extractUsername(d),
                extractName(d),
                extractPosts(d),
                extractFollowers(d),
                extractFollowing(d),
                extractBio(d)
        );
    }

    private String extractUsername(final Document d){
        return "";
    }

    private String extractName(final Document d){
        return "";
    }

    private int extractPosts(final Document d){
        return 0;
    }

    private int extractFollowers(final Document d){
        return 0;
    }

    private int extractFollowing(final Document d){
        return 0;
    }

    private String extractBio(final Document d){
        return "";
    }

    public String buildUrl(final String name) {
        return config.getUrl()
                .concat(name);
    }
}
