package dojo.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class UserManagement {
    private final List<User> userList;

    public UserManagement() {
        this.userList = new ArrayList<>();
    }

    public void addUser(User user){
        userList.add(user);
    }

    public User fetchUserByUserName(String UserName){
        return userList.stream().filter(user -> user.getUserName().equals(UserName)).findFirst().orElse(null);
    }

    public void post(User user, String post) {
        user.post(post);
        if (isPostMention(post)) {
            mention(post);
        }
    }

    private void mention(String post) {
        String[] words = post.split("\\s+");
        String mentionedUserName = null;
        for (String word : words) {
            if (word.startsWith("@")) {
                mentionedUserName = word.substring(1); // @を除去
                break;
            }
        }
        User mentionedUser = fetchUserByUserName(mentionedUserName);
        if (mentionedUser == null) {
            return;
        }
        mentionedUser.addReceiveMentions(post);
    }

    private boolean isPostMention(String post) {
        String[] words = post.split("\\s+");
        boolean containsAtSymbol = false;
        for (String word : words) {
            if (word.startsWith("@")) {
                containsAtSymbol = true;
                break;
            }
        }
        return containsAtSymbol;
    }
}
