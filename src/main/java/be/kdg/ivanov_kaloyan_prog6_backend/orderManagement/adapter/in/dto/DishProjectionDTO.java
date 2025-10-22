package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.FoodTagProjection;
import java.math.BigDecimal;
import java.util.List;

public record DishProjectionDTO(String dishId, String restaurantId,String stockStatus, String name, String type,
                                List<String> tags, String description, BigDecimal price, String pictureURL){


    public static DishProjectionDTO from(final DishProjection dishProjection){
        final List<String> tags = dishProjection.getTags().stream()
                .map(FoodTagProjection::name).toList();

        return new DishProjectionDTO(dishProjection.getId().toString(), dishProjection.getRestaurantId().toString(),dishProjection.getStockStatus().name(),
                dishProjection.getName(), dishProjection.getType().name(), tags, dishProjection.getDescription(),
                dishProjection.getPrice(), dishProjection.getPictureURL());
    }
}
