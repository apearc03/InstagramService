package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import instagramService.config.Config;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class JsonUserInfoBuilder {

    private final Config config;
    private final JsonNodeFactory factory = JsonNodeFactory.instance;

    @Inject
    public JsonUserInfoBuilder(final Config conf){
        config = conf;
    }


    public JsonNode buildUserJson(final JsonNode userInfo, final String username){
        final ObjectNode childNode = factory.objectNode();
        config.getFields()
                .stream()
                .forEach(field -> childNode.set(field, getAttributeValue(field, userInfo)));
        return factory.objectNode().set(username, childNode);
    }


    public JsonNode buildFailedUserJson(final String username) {
        final ObjectNode childNode = factory.objectNode();
        childNode.put(username, "error retrieving data for user: " + username + ". The user may not exist.");
        return factory.objectNode().set(username, childNode);
    }


    JsonNode getAttributeValue(final String field, final JsonNode userInfo){
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

    public JsonNode executorServiceError(){
        return factory.objectNode().put("Error", "There was an error processing your request");
    }


}
