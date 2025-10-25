package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables;

import jakarta.persistence.*;

@Embeddable
public class CustomerInfoEmbeddable {

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Embedded
    private LocationEmbeddable deliveryAddress;

    @Column(name = "customer_email", nullable = false)
    private String email;

    public CustomerInfoEmbeddable() {
    }

    public CustomerInfoEmbeddable(String name, LocationEmbeddable deliveryAddress, String email) {
        this.name = name;
        this.deliveryAddress = deliveryAddress;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public LocationEmbeddable getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getEmail() {
        return email;
    }
}
