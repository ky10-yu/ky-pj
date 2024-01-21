package dojo.socialnetwork;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    // Posting: Thomas can publish messages a message.
    @Test
    public void postFunctionTest() {
        User user = new User("THOMAS");
        user.post("post publish message");

        List<String> posts = user.fetchAllPost();

        List<String> expected = List.of("post publish message");
        assertEquals(expected, posts);
    }

    // Reading: Alice can view all Thomas’s messages.
    @Test
    public void readOtherUsersPostsFunctionTest() {
        User thomas = new User("THOMAS");
        User alice = new User("ALICE");
        thomas.post("thomas post publish message");

        List<String> posts = alice.fetchOtherUsersPosts(thomas);

        List<String> expected = List.of("thomas post publish message");
        assertEquals(expected, posts);
    }

    // Following: Charlie can subscribe to Thomas’s and Alice’s messages, and view an aggregated list of all subscriptions.
    @Test
    public void followingFunctionTest() {
        User thomas = new User("THOMAS");
        User alice = new User("ALICE");
        User charlie = new User("CHARLIE");
        thomas.post("thomas post publish message");
        alice.post("alice post publish message");
        charlie.follow(thomas);
        charlie.follow(alice);

        List<String> posts = charlie.fetchAllFollowUsersPosts();

        List<String> expected = List.of("thomas post publish message", "alice post publish message");
        assertEquals(expected, posts);
    }

    // Mentions: Alice can mention Charlie in a message using “@” like “@Charlie”
    @Test
    public void mentionFunctionTest() {
        UserManagement userManagement = new UserManagement();
        User alice = new User("ALICE");
        User charlie = new User("CHARLIE");
        userManagement.addUser(alice);
        userManagement.addUser(charlie);
        userManagement.post(alice, "@CHARLIE follow me");

        List<String> expected = List.of("@CHARLIE follow me");
        assertEquals(expected, charlie.fetchAllReceiveMentions());
        assertEquals(expected, alice.fetchAllPost());
    }

    @Test
    public void mentionedUserIsNotExistsTest() {
        UserManagement userManagement = new UserManagement();
        User alice = new User("ALICE");
        User charlie = new User("CHARLIE");
        userManagement.addUser(alice);
        userManagement.addUser(charlie);
        userManagement.post(alice, "@NOBODY follow me");

        List<String> expected = List.of("@NOBODY follow me");
        assertEquals(Collections.emptyList(), charlie.fetchAllReceiveMentions());
        assertEquals(expected, alice.fetchAllPost());
    }

    // Links: Thomas can share a link to a message
    @Test
    public void shareLinkFunctionTest() {
        UserManagement userManagement = new UserManagement();
        User thomas = new User("THOMAS");
        User alice = new User("ALICE");
        userManagement.addUser(thomas);
        userManagement.addUser(alice);
        alice.post("alice post publish message");

        thomas.shareLink(alice, 0);

        List<String> expected = List.of("https://socialnetwork/share_posts?user=ALICE&postIndex=0");
        assertEquals(expected, thomas.fetchAllPost());
    }

    // Direct Messages: Alice can send a private message to Thomas
    @Test
    public void directMessageFunctionTest() {
        UserManagement userManagement = new UserManagement();
        User thomas = new User("THOMAS");
        User alice = new User("ALICE");
        userManagement.addUser(thomas);
        userManagement.addUser(alice);

        alice.sendDirectMessage(thomas, "direct message");

        List<String> expected = List.of("direct message");
        assertEquals(expected, thomas.fetchDirectMessages(alice.getUserName()));
    }
}
