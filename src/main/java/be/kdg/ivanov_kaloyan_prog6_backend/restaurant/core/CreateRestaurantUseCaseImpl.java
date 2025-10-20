package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final LoadOwnerPort loadOwnerPort;

    private final List<UpdateMenuPort> updateMenuPorts;

    private final List<UpdateRestaurantPort> updateRestaurantPorts;

    public CreateRestaurantUseCaseImpl(final LoadOwnerPort loadOwnerPort,
                                       final  List<UpdateMenuPort> updateMenuPorts,
                                       final List<UpdateRestaurantPort> updateRestaurantPorts) {
        this.loadOwnerPort = loadOwnerPort;
        this.updateMenuPorts = updateMenuPorts;
        this.updateRestaurantPorts = updateRestaurantPorts;
    }

    @Override
    @Transactional
    public void createRestaurant(CreateRestaurantCommand command) {

        loadOwnerPort.loadBy(command.ownerId())
                .orElseThrow(() -> new NullPointerException("Owner not found: " + command.ownerId()));


        final Restaurant restaurant = Restaurant.create(command.ownerId(), command.address(),
                command.email(), command.pictureURL(), command.defaultPrepTime(), command.cuisineType(),
        command.openingHours());

        Menu menu = Menu.create(restaurant.getId().id());

        this.updateRestaurantPorts.forEach(port -> port.update(restaurant));

        this.updateMenuPorts.forEach(port -> port.update(menu));
    }
}
