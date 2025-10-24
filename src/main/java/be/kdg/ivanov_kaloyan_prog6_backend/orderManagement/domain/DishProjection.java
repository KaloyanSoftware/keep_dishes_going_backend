package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class DishProjection {

    private UUID id;

    private UUID restaurantId;

    private String stockStatus;

    private String name;

    private String type;

    private List<String> tags;

    private String description;

    private BigDecimal price;

    private String pictureURL;

    public DishProjection(UUID id, UUID restaurantId, String stockStatus, String name, String type,
                          List<String> tags, String description, BigDecimal price, String pictureURL) {
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

    public static DishProjection create(UUID id, UUID restaurantId, String stockStatus, String name, String type,
                                        List<String> tags, String description, BigDecimal price, String pictureURL){
        return new DishProjection(id,  restaurantId, stockStatus, name, type,
                tags, description, price, pictureURL);
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

    public List<String> getTags() {
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

    public UUID getId() {
        return id;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }
}
