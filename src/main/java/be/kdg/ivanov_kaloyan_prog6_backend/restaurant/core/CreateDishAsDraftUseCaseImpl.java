package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishSnapshot;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Restaurant;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateDishAsDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateDishAsDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class CreateDishAsDraftUseCaseImpl implements CreateDishAsDraftUseCase {

    private final LoadRestaurantPort loadRestaurantPort;

    private final SaveRestaurantPort saveRestaurantPort;

    public CreateDishAsDraftUseCaseImpl(final LoadRestaurantPort loadRestaurantPort,
                                        final SaveRestaurantPort saveRestaurantPort) {
        this.loadRestaurantPort = loadRestaurantPort;
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    public void createDishAsDraft(CreateDishAsDraftCommand command) {
        Restaurant restaurant = loadRestaurantPort.loadBy(command.restaurantId())
                .orElseThrow(() -> new NullPointerException("Restaurant not found"));

        // Build the draft snapshot from the command
        DishSnapshot draft = new DishSnapshot(
                command.name(),
                command.type(),
                command.tags(),
                command.description(),
                command.price(),
                command.pictureURL()
        );

        restaurant.getMenu().addDish(draft);

        saveRestaurantPort.save(restaurant);
    }
}
