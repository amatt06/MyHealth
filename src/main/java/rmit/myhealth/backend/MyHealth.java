package rmit.myhealth.backend;

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

    public void login(String username, String password) throws Exception {
        if (currentUser != null) {
            throw new Exception("A user is already logged in");
        }

        User user = userController.getUser(username);

        if (user == null || !UserAuthenticator.authenticate(user, password)) {
            throw new Exception("Invalid username or password");
        }

        currentUser = user;
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
