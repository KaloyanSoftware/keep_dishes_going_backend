package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final LoadOwnerPort loadOwnerPort;

    private final UpdateMenuPort updateMenuPort;

    private final List<UpdateRestaurantPort> updateRestaurantPorts;

    public CreateRestaurantUseCaseImpl(final LoadOwnerPort loadOwnerPort,
                                       final @Qualifier("jpa") UpdateMenuPort updateMenuPort,
                                       final List<UpdateRestaurantPort> updateRestaurantPorts) {
        this.loadOwnerPort = loadOwnerPort;
        this.updateMenuPort = updateMenuPort;
        this.updateRestaurantPorts = updateRestaurantPorts;
    }

    @Override
    @Transactional
    public Restaurant createRestaurant(CreateRestaurantCommand command) {

        loadOwnerPort.loadBy(command.ownerId())
                .orElseThrow(() -> new NullPointerException("Owner not found: " + command.ownerId()));


        final Restaurant restaurant = Restaurant.create(command.ownerId(), command.address(),
                command.email(), command.pictureURL(), command.defaultPrepTime(), command.cuisineType(),
        command.openingHours());

        Menu menu = Menu.create(restaurant.getId().id());

        Restaurant savedRestaurant = null;

        for (UpdateRestaurantPort port : updateRestaurantPorts) {
            Restaurant updated = port.update(restaurant);
            if (updated != null && updated.getId() != null) {
                savedRestaurant = updated;
            }
        }

        updateMenuPort.update(menu);

        return savedRestaurant != null ? savedRestaurant : restaurant;
    }
}
