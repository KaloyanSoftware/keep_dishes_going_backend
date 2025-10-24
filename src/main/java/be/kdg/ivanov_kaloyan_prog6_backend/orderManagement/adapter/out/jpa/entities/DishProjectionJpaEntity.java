package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.entities;
import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "order_management", name = "dish_projection")
public class DishProjectionJpaEntity {

    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "stock_status", nullable = false)
    private String stockStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "dish_projection_tag",
            schema = "order_management",
            joinColumns = @JoinColumn(name = "dish_id", referencedColumnName = "uuid")
    )

    @Column(name = "tag", nullable = false)
    private List<String> tags;

    public DishProjectionJpaEntity(UUID id, UUID restaurantId, String stockStatus,
                                   String name, String type, String description,
                                   Double price, String pictureUrl, List<String> tags) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.stockStatus = stockStatus;
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.tags = tags;
    }

    public DishProjectionJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
