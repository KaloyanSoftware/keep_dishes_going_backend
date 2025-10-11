package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final SaveRestaurantPort saveRestaurantPort;

    private final LoadOwnerPort loadOwnerPort;

    private final SaveMenuPort saveMenuPort;

    public CreateRestaurantUseCaseImpl(final SaveRestaurantPort saveRestaurantPort,
                                       final LoadOwnerPort loadOwnerPort,
                                       final SaveMenuPort saveMenuPort) {
        this.saveRestaurantPort = saveRestaurantPort;
        this.loadOwnerPort = loadOwnerPort;
        this.saveMenuPort = saveMenuPort;
    }

    @Override
    public Restaurant createRestaurant(CreateRestaurantCommand command) {

        loadOwnerPort.loadBy(command.ownerId())
                .orElseThrow(() -> new NullPointerException("Owner not found: " + command.ownerId()));

        final Restaurant restaurant = new Restaurant(
                OwnerId.of(command.ownerId()),
                command.address(),
                command.email(),
                command.pictureURL(),
                command.defaultPrepTime(),
                command.cuisineType(),
                command.openingHours()
        );

        Menu menu = new Menu(restaurant.getId());

        saveRestaurantPort.save(restaurant);

        saveMenuPort.save(menu);

        return restaurant;
    }
}
