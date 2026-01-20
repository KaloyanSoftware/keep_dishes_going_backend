package be.kdg.ivanov_kaloyan_prog6_backend;

import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.Menu;
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

    public DeleteDishUseCaseUnitTest(DeleteDishUseCase sut, LoadMenuPort loadMenuPort,
                                     @Qualifier("jpa") UpdateMenuPort updateMenuPort, TestHelper helper) {
        this.sut = sut;
        this.loadMenuPort = loadMenuPort;
        this.updateMenuPort = updateMenuPort;
        this.helper = helper;
    }

    @BeforeEach
    public void setUp(){

    }

    @AfterEach
    public void cleanUp(){
        helper.cleanUp();
    }

    @Test
    public void onDishDeletedSuccessfully(){
        //final Menu menu =
    }




}
