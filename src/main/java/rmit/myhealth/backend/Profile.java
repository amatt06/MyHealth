package rmit.myhealth.backend;

import java.awt.Image;

public class Profile {
    private String firstName;
    private String lastName;
    private Image profilePicture;

    // Constructor
    public Profile(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Overload Constructor if Image included.
    public Profile(String firstName, String lastName, Image profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
}

