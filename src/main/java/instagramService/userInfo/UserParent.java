package instagramService.userInfo;

public class UserParent {

    public String username;
    public UserInfo userinfo;

    public UserParent(final String name) {
        this.username = name;
    }

    public UserParent(final String name, final UserInfo user) {
        this.username = name;
        this.userinfo = user;
    }
}
