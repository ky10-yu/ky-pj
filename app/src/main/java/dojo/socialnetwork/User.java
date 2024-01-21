package dojo.socialnetwork;

import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userName;
    private final List<String> posts;
    private final List<User> followUsers;

    public User(String userName) {
        this.userName = userName;
        this.posts = new ArrayList<>();
        this.followUsers = new ArrayList<>();
    }

    public String getUserName(){
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
}
