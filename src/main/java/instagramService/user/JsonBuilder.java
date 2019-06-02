package instagramService.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
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

    //TODO Clean up how this is done.
    public Map<String, Map<String, Object>> buildJsonResponse(final JsonNode userInfo, final String username){
        Map<String, Object> userData = new HashMap<>();
        config.getFields()
                .stream()
                .forEach(field -> userData.put(field, getAttributeValue(field, userInfo)));
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put(username, userData)
                .build();
    }

    //TODO Clean up how this is done.
    public  Map<String, Map<String, Object>> buildFailedResponse(final String username) {
        Map<String, Object> failedUserData = new HashMap<>();
        failedUserData.put("error", "error retrieving data for user: " + username);
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put(username, failedUserData)
                .build();
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
