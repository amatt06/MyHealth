package rmit.myhealth.backend;

import java.util.HashMap;
import java.util.Map;

public class UserController {
    private Map<String, User> users;

    public UserController() {
        users = new HashMap<String, User>();
    }

    public void createUser(String username, String password, String firstName, String lastName) {
        Profile profile = new Profile(firstName, lastName);
        User newUser = new User(username, password, profile);
        users.put(username, newUser);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}

