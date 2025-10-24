package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class BasketLine{
    private UUID restaurantId;

    private UUID dishId;

    private String name;

    private BigDecimal price;

    private Integer quantity = 1;

    private String pictureURL;

    public BasketLine(UUID restaurantId, UUID dishId, String name,
                      BigDecimal price, String pictureURL) {
        this.restaurantId = restaurantId;
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.pictureURL = pictureURL;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public Integer decreaseQuantity(){
        return --quantity;
    }

    public UUID getRestaurantId() {
        return restaurantId;
    }

    public UUID getDishId() {
        return dishId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "BasketLine{" +
                "quantity=" + quantity +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", dishId=" + dishId +
                ", restaurantId=";
    }
}
