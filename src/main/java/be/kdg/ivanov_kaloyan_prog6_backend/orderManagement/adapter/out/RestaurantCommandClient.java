package be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.adapter.out;

import be.kdg.ivanov_kaloyan_prog6_backend.common.commands.GetPublishedDishCommand;
import be.kdg.ivanov_kaloyan_prog6_backend.common.responses.PublishedDishResponse;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.exceptions.ItemResponseError;
import be.kdg.ivanov_kaloyan_prog6_backend.orderManagement.port.out.PublishedDishCatalog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantCommandClient implements PublishedDishCatalog {

    private final WebClient client;

    public RestaurantCommandClient(
            WebClient.Builder builder,
            @Value("http://localhost:8080") String baseUrl
    ) {
        this.client = builder.baseUrl(baseUrl).build();
    }


    @Override
    public Optional<PublishedItemSnapshot> findPublishedDish(UUID restaurantId, UUID dishId) {
        var cmd = new GetPublishedDishCommand(restaurantId, dishId);

        var resp = client.post()
                .uri("/api/commands/restaurant/get-published-dish")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cmd)
                .retrieve()
                .toEntity(PublishedDishResponse.class)
                .block();

        if (resp == null || !resp.getStatusCode().is2xxSuccessful() || resp.getBody() == null) {
            throw new ItemResponseError("Error getting dish details with dish id: " + dishId +
                    " and restaurant id: " + restaurantId);
        }

        var responseBody = resp.getBody();
        return Optional.of(new PublishedItemSnapshot(
                responseBody.dishId(), responseBody.restaurantId(), responseBody.name(),
                responseBody.priceCents(), responseBody.pictureUrl(), responseBody.outOfStock()
        ));
    }
}
