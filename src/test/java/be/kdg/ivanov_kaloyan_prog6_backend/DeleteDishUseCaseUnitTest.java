package be.kdg.ivanov_kaloyan_prog6_backend;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Dish;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.DishType;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.FoodTag;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.commands.DeleteDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.in.useCases.DeleteDishUseCase;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.LoadMenuPort;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.port.out.UpdateMenuPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class DeleteDishUseCaseUnitTest {

    @Autowired
    private final DeleteDishUseCase sut;

    @MockitoBean
    private final LoadMenuPort loadMenuPort;

    @MockitoBean
    private final UpdateMenuPort updateMenuPort;

    @Autowired
    private final TestHelper helper;

    private Menu menu;

    private UUID dishId;

    public DeleteDishUseCaseUnitTest(DeleteDishUseCase sut, LoadMenuPort loadMenuPort,
                                     @Qualifier("jpa") UpdateMenuPort updateMenuPort, TestHelper helper) {
        this.sut = sut;
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPort = updateMenuPort;
        this.helper = helper;
    }

    @BeforeEach
    public void setUp(){
        final UUID menuId = UUID.randomUUID();
        final Map<UUID, Dish> dishes = new HashMap<>();
        final Dish dish = helper.createDish(UUID.randomUUID(), menuId, Dish.State.UNPUBLISHED, Dish.StockStatus.IN_STOCK,
                "Pizza Margharitta", DishType.MAIN, List.of(FoodTag.GLUTEN, FoodTag.LACTOSE), "Cheesy pizza",
                BigDecimal.valueOf(20), "imageURL");
        dishes.put(UUID.randomUUID(), dish);

        this.menu = helper.createMenuWithDishes(menuId, UUID.randomUUID(), dishes);
        this.dishId = dish.getId().id();
    }

    @AfterEach
    public void cleanUp(){
        helper.cleanUp();
    }

    @Test
    public void shouldDeleteDishSuccessfully(){
        //Arrange

        //Act

        //Assert


    }




}
