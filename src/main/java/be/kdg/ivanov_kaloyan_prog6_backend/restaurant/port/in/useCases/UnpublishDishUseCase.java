package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.UnpublishDishCommand;

public interface UnpublishDishUseCase {
    void unpublish(UnpublishDishCommand command);

}
