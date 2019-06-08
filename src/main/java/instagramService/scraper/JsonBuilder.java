package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import instagramService.config.Config;
import org.springframework.stereotype.Component;
import springfox.documentation.spring.web.json.Json;

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

    /*public Map<String, Map<String, Object>> buildJsonResponse(final JsonNode userInfo, final String username){
        Map<String, Object> userData = new HashMap<>();
        config.getFields()
                .stream()
                .forEach(field -> userData.put(field, getAttributeValue(field, userInfo)));
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put(username, userData)
                .build();
    }*/

    /*public  Map<String, Map<String, Object>> buildFailedResponse(final String username) {
        Map<String, Object> failedUserData = new HashMap<>();
        failedUserData.put("error", "error retrieving data for user: " + username + ". The user may not exist.");
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put(username, failedUserData)
                .build();
    }*/

    public Map<String, Map<String, Object>> buildJsonResponse(final JsonNode userInfo, final String username){

        Map<String, Object> userData = new HashMap<>();
        config.getFields()
                .stream()
                .forEach(field -> userData.put(field, getAttributeValue(field, userInfo)));
        return ImmutableMap.<String, Map<String, Object>>builder()
                .put(username, userData)
                .build();
    }


    public JsonNode buildFailedResponse(final String username) {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode child = factory.objectNode();
        child.put("error", "error retrieving data for user: " + username + ". The user may not exist.");
        ObjectNode j = factory.objectNode();
        j.set("error",child);
        return j;
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
