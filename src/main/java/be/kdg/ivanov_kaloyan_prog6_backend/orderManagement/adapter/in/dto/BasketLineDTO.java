package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.in.dto;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.BasketLine;

public record BasketLineDTO(String dishId,
                            String restaurantId,
                            String name,
                            Double price,
                            Integer quantity,
                            String pictureURL) {

    public static BasketLineDTO from(final BasketLine basketLine){
        return new BasketLineDTO(basketLine.getDishId().toString(),
                basketLine.getRestaurantId().toString(),
                basketLine.getName(),
                basketLine.getPrice().doubleValue(),
                basketLine.getQuantity(),
                basketLine.getPictureURL());
    }
}
