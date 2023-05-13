package rmit.myhealth.backend;

public class User {
    private String username;
    private String password;
    private Profile profile;
    private HealthRecordController healthRecordController;

    public User(String username, String password, Profile profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
        this.healthRecordController = new HealthRecordController();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public HealthRecordController getHealthRecordController() {
        return healthRecordController;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

