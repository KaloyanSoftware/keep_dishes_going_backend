package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.CreateRestaurantUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveRestaurantPort;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase {

    private final SaveRestaurantPort saveRestaurantPort;

    public CreateRestaurantUseCaseImpl(SaveRestaurantPort saveRestaurantPort) {
        this.saveRestaurantPort = saveRestaurantPort;
    }

    @Override
    public void createRestaurant(CreateRestaurantCommand command) {

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
                OwnerId.of(command.ownerId()),
                address,
                command.email(),
                command.pictureURL(),
                command.defaultPrepTime(),
                command.cuisineType(),
                openingHours
        );

        saveRestaurantPort.save(restaurant);
    }
}
