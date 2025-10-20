package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out;


import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;

public interface UpdateRestaurantProjectionPort {
    void update(RestaurantProjection restaurant);
}
