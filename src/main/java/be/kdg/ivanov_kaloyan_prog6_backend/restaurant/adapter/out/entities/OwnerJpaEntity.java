package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "owner", schema = "restaurant")
public class OwnerJpaEntity {
    @Id
    @Column(name = "uuid")
    private UUID id;

    @Column(name="first_name", nullable=false)
    private String firstName;

    @Column(name="last_name", nullable=false)
    private String lastName;

    public OwnerJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
