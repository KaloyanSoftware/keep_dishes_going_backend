package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public class Owner {
    private OwnerId id;

    private String firstName;

    private String lastName;

    private String email;

    public Owner(OwnerId ownerId, String firstName, String lastName, String email) {
        setId(ownerId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }


    public OwnerId getId() {
        return id;
    }

    public void setId(OwnerId id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
