package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Owner;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OwnerId;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.RegisterOwnerCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.RegisterOwnerUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveOwnerPort;
import org.springframework.stereotype.Service;

@Service
public class RegisterOwnerUseCaseImpl implements RegisterOwnerUseCase {

    private final SaveOwnerPort saveOwnerPort;

    public RegisterOwnerUseCaseImpl(final SaveOwnerPort saveOwnerPort) {
        this.saveOwnerPort = saveOwnerPort;
    }


    @Override
    public void register(RegisterOwnerCommand command) {

        Owner owner = new Owner(OwnerId.of(command.id()), command.firstName(), command.lastName(), command.email());

        saveOwnerPort.save(owner);
    }
}
