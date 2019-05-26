package instagramService.user;


public class UserInfo {

    private String username;
    private String name;
    private int posts;
    private int followers;
    private int following;
    private String bio;

    public UserInfo(final String user, final String name, final int posts, final int followers, final int following, final String bio){
        this.username = user;
        this.name = name;
        this.posts = posts;
        this.followers = followers;
        this.following = following;
        this.bio = bio;
    }

}
