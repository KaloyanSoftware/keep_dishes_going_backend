package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.DishDraftJpaEntity;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.DishDraftMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers.MoneyMapper;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.DishDraftJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishDraft;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.DeleteDishDraftPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadDishDraftPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.SaveDishDraftPort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Qualifier("jpa")
@Profile("jpa")
public class DishDraftJpaAdapter implements SaveDishDraftPort, LoadDishDraftPort, DeleteDishDraftPort {

    private final DishDraftJpaRepository drafts;

    private final DishDraftMapper dishDraftMapper;

    private final MoneyMapper moneyMapper;

    public DishDraftJpaAdapter(final DishDraftJpaRepository drafts,
                               final DishDraftMapper dishDraftMapper,
                               final MoneyMapper moneyMapper) {
        this.drafts = drafts;
        this.dishDraftMapper = dishDraftMapper;
        this.moneyMapper = moneyMapper;
    }

    @Override
    public DishDraft save(DishDraft draft) {
        DishDraftJpaEntity dishDraftJpaEntity = dishDraftMapper.toJpa(draft);

        DishDraftJpaEntity savedEntity = drafts.save(dishDraftJpaEntity);

        return dishDraftMapper.toDomain(savedEntity, moneyMapper);
    }

    @Override
    public Optional<DishDraft> loadBy(UUID id) {
        return this.drafts.findById(id).map(draft -> dishDraftMapper.toDomain(draft, moneyMapper));
    }

    @Override
    public List<DishDraft> loadByRestaurant(UUID id) {
        return drafts.getAllByRestaurantId(id).stream().map(draft -> dishDraftMapper.toDomain(draft, moneyMapper)).toList();
    }

    @Override
    public void delete(UUID id) {
        drafts.deleteById(id);
    }
}
