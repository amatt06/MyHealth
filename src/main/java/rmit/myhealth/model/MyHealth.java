package rmit.myhealth.model;

public class MyHealth {

    private static MyHealth instance;
    private UserController userController;
    private User currentUser;

    public MyHealth() {
        this.userController = new UserController();
        this.currentUser = null;
    }

    public static MyHealth getInstance() {
        if (instance == null) {
            instance = new MyHealth();
        }
        return instance;
    }

    public UserController getUserController() {
        return this.userController;
    }

    public boolean login(String username, String password) {
        if (currentUser != null) {
            // A user is already logged in
            return false;
        }

        User user = userController.getUser(username);

        if (user == null || !UserAuthenticator.authenticate(user, password)) {
            // Invalid username or password
            return false;
        }

        currentUser = user;
        return true;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void createUser(String username, String password, String firstName, String lastName) {
        userController.createUser(username, password, firstName, lastName);
    }
}
