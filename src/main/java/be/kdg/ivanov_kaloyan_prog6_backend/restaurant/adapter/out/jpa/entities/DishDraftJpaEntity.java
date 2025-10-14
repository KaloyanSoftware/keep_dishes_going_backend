package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "restaurant", name = "dish_draft")
public class DishDraftJpaEntity {
    @Id
    @Column(name = "uuid", nullable = false)
    private UUID id;

    @Column(name = "restaurant_id", nullable = false)
    private UUID restaurantId;

    @Column(name = "dish_id")
    private UUID dishId;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private DishType type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "draft_tag",
            schema = "restaurant",
            joinColumns = @JoinColumn(name = "draft_id", referencedColumnName = "uuid")
    )

    @Enumerated(EnumType.STRING)
    @Column(name = "draft_tag", nullable = false)
    private List<FoodTag> draftTags;

    public DishDraftJpaEntity(UUID id, UUID restaurantId, UUID dishId, String name,
                              DishType type, String description, Double price,
                              String pictureUrl, List<FoodTag> draftTags) {
        setId(id);
        setRestaurantId(restaurantId);
        setDishId(dishId);
        setName(name);
        setType(type);
        setDescription(description);
        setPrice(price);
        setPictureUrl(pictureUrl);
        setDraftTags(draftTags);
    }

    public DishDraftJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(UUID restaurantId) {
        this.restaurantId = restaurantId;
    }

    public UUID getDishId() {
        return dishId;
    }

    public void setDishId(UUID dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getType() {
        return type;
    }

    public void setType(DishType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public List<FoodTag> getDraftTags() {
        return draftTags;
    }

    public void setDraftTags(List<FoodTag> draft_tags) {
        this.draftTags = draft_tags;
    }
}
