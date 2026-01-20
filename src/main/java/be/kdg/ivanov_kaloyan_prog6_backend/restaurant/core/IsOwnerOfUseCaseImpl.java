package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.IsOwnerOfCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.IsOwnerOfUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadRestaurantPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class IsOwnerOfUseCaseImpl implements IsOwnerOfUseCase {

    private final LoadRestaurantPort port;

    public IsOwnerOfUseCaseImpl(final LoadRestaurantPort port) {
        this.port = port;
    }


    @Override
    public boolean isOwnerOf(IsOwnerOfCommand command) {
        return port.existsByIdAndOwnerId(command.restaurantId(), command.ownerId());
    }
}
