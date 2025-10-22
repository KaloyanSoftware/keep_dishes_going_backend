package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.core;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.RestaurantProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.in.useCases.GetAlRestaurantProjectionsUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.LoadRestaurantProjectionPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GetAllRestaurantProjectionsUseCaseImpl implements GetAlRestaurantProjectionsUseCase {

    private final LoadRestaurantProjectionPort loadRestaurantProjectionPort;

    public GetAllRestaurantProjectionsUseCaseImpl(final LoadRestaurantProjectionPort loadRestaurantProjectionPort) {
        this.loadRestaurantProjectionPort = loadRestaurantProjectionPort;
    }

    @Override
    public List<RestaurantProjection> getAll() {
        return loadRestaurantProjectionPort.loadAll();
    }
}
