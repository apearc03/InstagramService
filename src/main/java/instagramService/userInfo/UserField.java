package instagramService.userInfo;

public enum UserField {
    full_name("full_name"),
    biography("biography"),
    followersCount("followersCount"),
    followingCount("followingCount"),
    postsCount("postsCount"),
    is_private("is_private"),
    is_verified("is_verified"),
    profile_pic_url("profile_pic_url"),
    profile_pic_url_hd("profile_pic_url_hd"),
    is_business_account("is_business_account"),
    is_joined_recently("is_joined_recently"),
    highlight_reel_count("highlight_reel_count");

    private String name;

    UserField(final String fieldName){
        name = fieldName;
    }

    public String getName() {
        return name;
    }
}
