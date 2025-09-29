package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveOwnerPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final LoadOwnerPort loadOwnerPort;

    private final SaveRestaurantPort saveRestaurantPort;

    private final SaveOwnerPort saveOwnerPort;

    public CreateRestaurantUseCaseImpl(LoadOwnerPort loadOwnerPort,
                                       SaveRestaurantPort saveRestaurantPort,
                                       SaveOwnerPort saveOwnerPort) {
        this.loadOwnerPort = loadOwnerPort;
        this.saveRestaurantPort = saveRestaurantPort;
        this.saveOwnerPort = saveOwnerPort;
    }

    @Override
    public void createRestaurant(CreateRestaurantCommand command) {
        final Owner owner = loadOwnerPort.loadBy(UUID.fromString(command.ownerId()))
                .orElseThrow();


        final Address address = new Address(
                command.addressDTO().street(),
                command.addressDTO().number(),
                command.addressDTO().postalCode(),
                command.addressDTO().city(),
                command.addressDTO().country()
        );

        final OpeningHours openingHours = new OpeningHours(
                command.openingHoursDTO().weeklySchedule().entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey, // DayOfWeek
                                e -> new DaySchedule(e.getValue().start(), e.getValue().end())
                        ))
        );


        final Restaurant restaurant = new Restaurant(
                address,
                command.email(),
                command.pictureURL(),
                command.defaultPrepTime(),
                command.cuisineType(),
                openingHours
        );

        owner.assignRestaurant(restaurant);

        saveOwnerPort.save(owner);
        saveRestaurantPort.save(restaurant);
    }
}
