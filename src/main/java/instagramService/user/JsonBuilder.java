package instagramService.user;

import com.fasterxml.jackson.databind.JsonNode;
import instagramService.config.Config;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@Component
public class JsonBuilder {

    private final Config config;

    @Inject
    public JsonBuilder(final Config conf){
        config = conf;
    }

    public Map<String, Object> buildJsonResponse(final JsonNode userInfo){
        //For each item in list add the field name and the value from userInfo to map
        Map<String, Object> result = new HashMap<>();
        config.getFields()
                .stream()
                .forEach(field -> result.put(field, getAttributeValue(field, userInfo)));
        return result;
    }


    public Map<String,Object> buildFailedResponse() {
        //TODO
        Map<String, Object> m = new HashMap<>();
        m.put("worked", false);
        return m;
    }


    Object getAttributeValue(final String field, final JsonNode userInfo){
        switch (field){
            case "followersCount":
                return userInfo.get("edge_followed_by").get("count");
            case "followingCount":
                return userInfo.get("edge_follow").get("count");
            case "postsCount":
                return userInfo.get("edge_owner_to_timeline_media").get("count");
            default:
                return userInfo.get(field);
        }
    }

}
