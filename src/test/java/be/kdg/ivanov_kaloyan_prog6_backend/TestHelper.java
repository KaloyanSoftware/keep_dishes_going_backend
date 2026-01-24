package be.kdg.ivanov_kaloyan_prog6_backend;

import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.DishProjectionJpaRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.OrderRepository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out.jpa.repositories.RestaurantProjectionJpaRespository;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.domain.DishProjection;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.repositories.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class TestHelper {

    private final MenuJpaRepository  menuJpaRepository;

    private final DishDraftJpaRepository dishDraftJpaRepository;

    private final OrderProjectionJpaRepository orderProjectionJpaRepository;

    private final OwnerJpaRepository ownerJpaRepository;

    private final RestaurantJpaRepository restaurantJpaRepository;

    private final DishProjectionJpaRepository dishProjectionJpaRepository;

    private final OrderRepository orderRepository;

    private final RestaurantProjectionJpaRespository restaurantProjectionJpaRespository;

    public TestHelper(MenuJpaRepository menuJpaRepository, DishDraftJpaRepository dishDraftJpaRepository,
                      OrderProjectionJpaRepository orderProjectionJpaRepository,
                      OwnerJpaRepository ownerJpaRepository, RestaurantJpaRepository restaurantJpaRepository,
                      DishProjectionJpaRepository dishProjectionJpaRepository, OrderRepository orderRepository,
                      RestaurantProjectionJpaRespository restaurantProjectionJpaRespository) {
        this.menuJpaRepository = menuJpaRepository;
        this.dishDraftJpaRepository = dishDraftJpaRepository;
        this.orderProjectionJpaRepository = orderProjectionJpaRepository;
        this.ownerJpaRepository = ownerJpaRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.dishProjectionJpaRepository = dishProjectionJpaRepository;
        this.orderRepository = orderRepository;
        this.restaurantProjectionJpaRespository = restaurantProjectionJpaRespository;
    }

    public void cleanUp(){
        menuJpaRepository.deleteAll();
        dishDraftJpaRepository.deleteAll();
        orderProjectionJpaRepository.deleteAll();
        ownerJpaRepository.deleteAll();
        restaurantJpaRepository.deleteAll();
        dishProjectionJpaRepository.deleteAll();
        orderRepository.deleteAll();
        restaurantProjectionJpaRespository.deleteAll();
    }

    public Menu createMenuWithDishes(UUID menuId, UUID restaurantId, Map<UUID, Dish> dishes){
        return new Menu(MenuId.of(menuId), RestaurantId.of(restaurantId), dishes);
    }

    public Dish createDish(UUID id, UUID menuId, Dish.State state, Dish.StockStatus stockStatus, String name, DishType type,
                           List<FoodTag> tags, String description, BigDecimal price, String pictureURL){
        return new Dish(DishId.of(id), MenuId.of(menuId), state, stockStatus, name, type, tags, description, price, pictureURL);
    }
}
