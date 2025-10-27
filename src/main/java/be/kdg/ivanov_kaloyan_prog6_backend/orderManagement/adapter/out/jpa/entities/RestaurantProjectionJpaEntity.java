package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.embeddables.LocationEmbeddable;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(schema = "order_management", name = "restaurant_projection")
public class RestaurantProjectionJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Embedded
    private LocationEmbeddable location;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "picture_url")
    private String pictureURL;

    @Column(name = "default_prep_min", nullable = false)
    private Integer defaultPrepTime;

    @Column(name = "cuisine_type", nullable = false)
    private String cuisineType;

    @Column(name = "is_open", nullable = false)
    private boolean isOpen;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public LocationEmbeddable getLocation() { return location; }
    public void setLocation(LocationEmbeddable location) { this.location = location; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPictureURL() { return pictureURL; }
    public void setPictureURL(String pictureURL) { this.pictureURL = pictureURL; }

    public Integer getDefaultPrepTime() { return defaultPrepTime; }
    public void setDefaultPrepTime(Integer defaultPrepTime) { this.defaultPrepTime = defaultPrepTime; }

    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }

    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }
}
