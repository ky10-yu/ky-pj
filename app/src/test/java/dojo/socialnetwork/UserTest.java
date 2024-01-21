package dojo.socialnetwork;

import org.junit.Test;

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
}
