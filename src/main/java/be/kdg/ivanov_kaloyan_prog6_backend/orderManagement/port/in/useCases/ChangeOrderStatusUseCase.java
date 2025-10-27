package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.ChangeOrderStatusCommand;

public interface ChangeOrderStatusUseCase {
    void changeStatus(ChangeOrderStatusCommand command);
}
