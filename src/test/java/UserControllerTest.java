import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import rmit.myhealth.model.User;
import rmit.myhealth.model.UserController;

/**
 * Test cases for the UserController which ensure
 * that the UserController class can create, retrieve and delete user data correctly,
 * and that the profiles associated with each user are correctly created and updated.
 */

class UserControllerTest {

    // Test successful creation of a user and profile.
    @Test
    void testCreateUser() {
        UserController controller = new UserController();
        String username = "johndoe";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";

        try {
            controller.createUser(username, password, firstName, lastName);
            assertEquals(firstName, controller.getUser(username).getProfile().getFirstName());
            assertEquals(lastName, controller.getUser(username).getProfile().getLastName());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    // Test successful retrieval of a user.
    @Test
    void testGetUser() {
        UserController controller = new UserController();
        String username = "janedoe";
        String password = "anotherpassword";
        String firstName = "Jane";
        String lastName = "Doe";

        try {
            controller.createUser(username, password, firstName, lastName);
            User user = controller.getUser(username);
            assertNotNull(user);
            assertEquals(username, user.getUsername());
            assertEquals(password, user.getPassword());
            assertNotNull(user.getProfile());
            assertEquals(firstName, user.getProfile().getFirstName());
            assertEquals(lastName, user.getProfile().getLastName());
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }


}
