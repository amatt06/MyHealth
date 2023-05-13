package rmit.myhealth.backend;

public class UserAuthenticator {
    public static boolean authenticate(User user, String password) {
        return user.getPassword().equals(password);
    }
}
