package instagramService.scraper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import instagramService.userInfo.UserField;
import instagramService.userInfo.UserInfo;
import instagramService.userInfo.UserParent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.stream.Stream;

@Component
public class JsonUserInfoBuilder {

    private final ObjectMapper mapper;
    private static final JsonNodeFactory FACTORY = JsonNodeFactory.instance;

    @Inject
    public JsonUserInfoBuilder(final ObjectMapper map){
        this.mapper = map;
    }


    public UserParent buildUserJson(final JsonNode userInfo, final String username){
        final ObjectNode childNode = FACTORY.objectNode();
        Stream.of(
                UserField.values()
        ).forEach(userField -> childNode.set(userField.getName(), getAttributeValue(userField.getName(), userInfo)));
        final UserParent userParent = new UserParent(username, mapper.convertValue(childNode, UserInfo.class));
        return userParent;
    }


    public UserParent buildFailedUserJson(final String username) {
        return new UserParent(username);
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


}
