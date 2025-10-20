package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class DishProjection {

    private DishProjectionId id;

    private RestaurantProjectionId restaurantId;

    private StockStatus stockStatus;

    private String name;

    private DishType type;

    private List<FoodTagProjection> tags;

    private String description;

    private BigDecimal price;

    private String pictureURL;

    public enum StockStatus {
        IN_STOCK,
        OUT_OF_STOCK

    }

    public enum DishType {
        STARTER, MAIN, DESSERT
    }

    public DishProjection(DishProjectionId id, RestaurantProjectionId restaurantId, StockStatus stockStatus, String name, DishType type,
                          List<FoodTagProjection> tags, String description, BigDecimal price, String pictureURL) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.stockStatus = stockStatus;
        this.name = name;
        this.type = type;
        this.tags = tags;
        this.description = description;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public static DishProjection create(UUID id, UUID restaurantId, StockStatus stockStatus, String name, DishType type,
                                        List<FoodTagProjection> tags, String description, BigDecimal price, String pictureURL){
        return new DishProjection(DishProjectionId.of(id),  RestaurantProjectionId.of(restaurantId), stockStatus, name, type,
                tags, description, price, pictureURL);
    }

    public DishProjectionId getId() {
        return id;
    }

    public RestaurantProjectionId getRestaurantId() {
        return restaurantId;
    }

    public StockStatus getStockStatus() {
        return stockStatus;
    }

    public String getName() {
        return name;
    }

    public DishType getType() {
        return type;
    }

    public List<FoodTagProjection> getTags() {
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
