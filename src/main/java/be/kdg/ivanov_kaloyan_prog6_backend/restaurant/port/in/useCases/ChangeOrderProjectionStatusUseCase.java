package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.OrderProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.ChangeOrderProjectionStatusCommand;

public interface ChangeOrderProjectionStatusUseCase {
    OrderProjection changeStatus(ChangeOrderProjectionStatusCommand command);

}
