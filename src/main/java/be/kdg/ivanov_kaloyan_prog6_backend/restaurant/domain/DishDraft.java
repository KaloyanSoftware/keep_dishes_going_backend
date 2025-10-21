package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain;

import java.math.BigDecimal;
import java.util.List;

public class DishDraft {

    private final DishDraftId id;
    private final RestaurantId restaurantId;
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
        this.id = DishDraftId.create();
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public DishDraft(DishDraftId draftId, RestaurantId restaurantId, DishId dishId, String name, DishType type,
                     List<FoodTag> tags, String description,
                     BigDecimal price, String pictureURL) {
        this.id = draftId;
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.name = name;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public DishDraftId getId() {
        return id;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public DishId getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public DishType getType() {
        return type;
    }

    public List<FoodTag> getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPictureURL() {
        return pictureURL;
    }
}
