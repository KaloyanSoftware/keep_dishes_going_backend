package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.UnpublishDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.UnpublishDishUseCase;
import org.springframework.stereotype.Service;

@Service
public class UnpublishDishUseCaseImpl implements UnpublishDishUseCase {

    @Override
    public void unpublish(UnpublishDishCommand command) {

    }
}
