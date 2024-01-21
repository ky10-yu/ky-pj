package dojo.socialnetwork;

import java.util.ArrayList;
import java.util.List;

public class DirectMessage {
    private final String toUserName;
    private final List<String> messages;


    public DirectMessage(String userName) {
        this.toUserName = userName;
        this.messages = new ArrayList<>();
    }

   public String getToUserName() {
        return this.toUserName;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public void addMessages(String message) {
        this.messages.add(message);
    }
}
