package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.DishDeleteProjector;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.commands.DishUnpublishedProjectionCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.DeleteDishProjectionPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DishDeletedProjectionImpl implements DishDeleteProjector {

    private final DeleteDishProjectionPort deleteDishProjectionPort;


    public DishDeletedProjectionImpl(final DeleteDishProjectionPort deleteDishProjectionPort) {
        this.deleteDishProjectionPort = deleteDishProjectionPort;
    }

    @Override
    public void project(DishUnpublishedProjectionCommand command) {
        deleteDishProjectionPort.delete(command.dishId());
    }
}
