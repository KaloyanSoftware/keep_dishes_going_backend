package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import java.math.BigDecimal;
import java.util.List;

public record DishDTO(String id, String menuId, String name,
                      String type, List<String> tags, String description,
                      BigDecimal price, String pictureURL, String state, String stockStatus) {

    public static DishDTO from(final Dish dish){
        final List<String> tags = dish.getTags().stream()
                .map(FoodTag::name).toList();

        return new DishDTO(dish.getId().id().toString(), dish.getMenuId().id().toString(), dish.getName(),
                dish.getType().name(),tags,dish.getDescription(), dish.getPrice(),
                dish.getPictureURL(), dish.getState().name(), dish.getStockStatus().name());
    }
}
