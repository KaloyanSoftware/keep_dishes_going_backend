package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public class Owner {

    private final OwnerId id;
    private String firstName;
    private String lastName;
    private String email;

    public Owner(OwnerId ownerId, String firstName, String lastName, String email) {
        this.id = ownerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public OwnerId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
