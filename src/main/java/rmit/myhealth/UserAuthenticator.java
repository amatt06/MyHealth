package rmit.myhealth;

public class UserAuthenticator {
    public static boolean authenticate(User user, String password) {
        return user.getPassword().equals(password);
    }
}
