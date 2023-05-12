import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import rmit.myhealth.User;
import rmit.myhealth.UserAuthenticator;
import rmit.myhealth.Profile;

/**
 * Test cases for the authentication of users using the UserAuthenticator class.
 * The tests verify that the authenticate method correctly authenticates users with
 * valid passwords, and rejects authentication attempts with incorrect passwords.
 */


class UserAuthenticatorTest {

    // Test that the correct password is authenticated and the incorrect one is not.
    @Test
    void testAuthenticate() {
        Profile profile = new Profile("John", "Doe");
        User user = new User("johndoe", "password", profile);
        String password = "password";

        assertTrue(UserAuthenticator.authenticate(user, password), "Authentication failed for valid credentials.");
        assertFalse(UserAuthenticator.authenticate(user, "wrongpassword"), "Incorrect password should not authenticate user");
    }
}
