package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.GetRestaurantForOwnerCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.GetRestaurantForOwnerUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetRestaurantForOwnerUseCaseImpl implements GetRestaurantForOwnerUseCase {

    private final LoadRestaurantPort loadRestaurantPort;

    public GetRestaurantForOwnerUseCaseImpl(final LoadRestaurantPort loadRestaurantPort) {
        this.loadRestaurantPort = loadRestaurantPort;
    }

    @Override
    public Restaurant getRestaurant(GetRestaurantForOwnerCommand command) {
        return loadRestaurantPort.loadByOwner(command.ownerId())
                .orElse(null);
    }
}
