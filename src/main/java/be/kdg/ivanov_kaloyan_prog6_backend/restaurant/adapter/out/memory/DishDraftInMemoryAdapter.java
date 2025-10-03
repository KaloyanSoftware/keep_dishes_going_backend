package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.memory;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveDishDraftPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@Qualifier("memory")
@Profile("memory")
public class DishDraftInMemoryAdapter implements SaveDishDraftPort {

    private final Map<UUID, DishDraft> dishDrafts = new HashMap<>();

    @Override
    public DishDraft save(DishDraft draft) {
        return dishDrafts.put(draft.getId().id(), draft);
    }
}
