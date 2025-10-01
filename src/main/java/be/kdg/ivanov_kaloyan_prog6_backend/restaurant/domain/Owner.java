package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

public class Owner {
    private OwnerId id;

    private String firstName;

    private String lastName;

    public Owner(String firstName, String lastName) {
        id = OwnerId.create();
        this.firstName = firstName;
        this.lastName = lastName;
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
}
