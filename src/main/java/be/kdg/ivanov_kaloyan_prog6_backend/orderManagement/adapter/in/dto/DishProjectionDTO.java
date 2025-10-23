package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import java.math.BigDecimal;
import java.util.List;

public record DishProjectionDTO(String dishId, String restaurantId,String stockStatus, String name, String type,
                                List<String> tags, String description, BigDecimal price, String pictureURL){


    public static DishProjectionDTO from(final DishProjection dishProjection){

        return new DishProjectionDTO(dishProjection.getId().toString(), dishProjection.getRestaurantId().toString(),dishProjection.getStockStatus(),
                dishProjection.getName(), dishProjection.getType(), dishProjection.getTags(), dishProjection.getDescription(),
                dishProjection.getPrice(), dishProjection.getPictureURL());
    }
}
