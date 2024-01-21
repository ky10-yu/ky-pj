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
}
