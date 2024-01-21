package dojo.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userName;
    private final List<String> posts;
    private final List<User> followUsers;
    private final List<String> receiveMentions;

    public User(String userName) {
        this.userName = userName;
        this.posts = new ArrayList<>();
        this.followUsers = new ArrayList<>();
        this.receiveMentions = new ArrayList<>();
    }

    public String getUserName() {
        return this.userName;
    }

    public void post(String post) {
        this.posts.add(post);
    }

    public List<String> fetchAllPost() {
        return this.posts;
    }

    public List<String> fetchOtherUsersPosts(User user) {
        return user.fetchAllPost();
    }

    public void follow(User user) {
        this.followUsers.add(user);
    }

    public List<String> fetchAllFollowUsersPosts() {
        List<String> followUsersPosts = new ArrayList<>();
        followUsers.forEach(followUser -> followUsersPosts.addAll(followUser.fetchAllPost()));
        return followUsersPosts;
    }

    public void addReceiveMentions(String mention) {
        this.receiveMentions.add(mention);
    }

    public List<String> fetchAllReceiveMentions() {
        return this.receiveMentions;
    }

    public void shareLink(User user, int postIndex) {
        if (user.fetchAllPost().size() < postIndex + 1) {
            System.out.print("There is no post with this index number");
            return;
        }
        String url = String.format("https://socialnetwork/share_posts?user=%s&postIndex=%d", user.getUserName(), postIndex);
        post(url);
    }
}
