package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.GetAllDishesForRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetAllDishProjectionsForRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadDishProjectionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class GetAllDishProjectionsForRestaurantUseCaseImpl implements GetAllDishProjectionsForRestaurantUseCase {

    private final LoadDishProjectionPort loadDishProjectionPort;

    public GetAllDishProjectionsForRestaurantUseCaseImpl(final LoadDishProjectionPort loadDishProjectionPort) {
        this.loadDishProjectionPort = loadDishProjectionPort;
    }

    @Override
    public List<DishProjection> getAllForRestaurant(GetAllDishesForRestaurantCommand command) {
        return loadDishProjectionPort.loadAllBy(command.restaurantId());
    }
}
