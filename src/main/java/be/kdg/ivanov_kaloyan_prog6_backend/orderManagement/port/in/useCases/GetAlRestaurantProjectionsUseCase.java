package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;

import java.util.List;

public interface GetAlRestaurantProjectionsUseCase {
    List<RestaurantProjection> getAll();
}
