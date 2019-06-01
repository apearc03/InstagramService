package instagramService.config;

import java.util.List;

public class Config {

    private String url;

    private List<String> fields;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fieldsToReturn) {
        this.fields = fieldsToReturn;
    }
}
