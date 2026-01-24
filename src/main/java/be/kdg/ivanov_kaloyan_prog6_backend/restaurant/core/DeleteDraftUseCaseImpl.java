package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.core;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.DeleteDraftCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DeleteDraftUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.DeleteDishDraftPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteDraftUseCaseImpl implements DeleteDraftUseCase {

    private final DeleteDishDraftPort port;

    public DeleteDraftUseCaseImpl(DeleteDishDraftPort port) {
        this.port = port;
    }

    @Override
    public void deleteDraft(DeleteDraftCommand command) {
        this.port.delete(command.draftId());
    }
}
