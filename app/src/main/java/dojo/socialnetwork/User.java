package dojo.socialnetwork;

import jdk.jfr.DataAmount;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String userName;
    private final List<String> posts;

    public User(String userName) {
        this.userName = userName;
        this.posts = new ArrayList<>();
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
}
