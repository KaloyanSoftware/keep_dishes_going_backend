package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.List;

public class DishDraft {

    private DishDraftId id;

    private RestaurantId restaurantId;

    private DishId dishId;

    private String name;

    private DishType type;

    private List<FoodTag> tags;

    private String description;

    private BigDecimal price;

    private String pictureURL;

    public DishDraft(RestaurantId restaurantId, DishId dishId, String name, DishType type,
                     List<FoodTag> tags, String description,
                     BigDecimal price, String pictureURL) {
        setId(DishDraftId.create());
        setDishId(dishId);
        setRestaurantId(restaurantId);
        setName(name);
        setType(type);
        setTags(tags);
        setDescription(description);
        setPrice(price);
        setPictureURL(pictureURL);
    }

    public DishDraftId getId() {
        return id;
    }

    public void setId(DishDraftId id) {
        this.id = id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(RestaurantId restaurantId) {
        this.restaurantId = restaurantId;
    }

    public DishId getDishId() {
        return dishId;
    }

    public void setDishId(DishId dishId) {
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

    public List<FoodTag> getTags() {
        return tags;
    }

    public void setTags(List<FoodTag> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
