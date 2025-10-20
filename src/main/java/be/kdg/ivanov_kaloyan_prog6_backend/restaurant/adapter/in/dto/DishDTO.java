package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishDTO(String id, UUID restaurantId, String name,
                      DishType type, List<FoodTag> tags, String description,
                      BigDecimal price, String pictureURL, Dish.State state, Dish.StockStatus stockStatus) {

    public static DishDTO from(final Dish dish){
        return new DishDTO(dish.getId().id().toString(), dish.getMenuId().id(), dish.getName(),
                dish.getType(),dish.getTags(),dish.getDescription(), dish.getPrice(),
                dish.getPictureURL(), dish.getState(), dish.getStockStatus());
    }
}
