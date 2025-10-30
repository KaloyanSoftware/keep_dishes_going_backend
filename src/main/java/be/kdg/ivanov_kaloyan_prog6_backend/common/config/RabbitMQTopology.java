package be.kdg.ivanov_kaloyan_prog6_backend.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTopology {

    public static final String EXCHANGE_NAME = "kdg.events";
    public static final String ORDER_PICKEDUP_QUEUE = "delivery.order.pickedup";
    public static final String ORDER_DELIVERED_QUEUE = "delivery.order.delivered";
    public static final String ORDER_LOCATION_QUEUE = "delivery.order.location";

    @Bean
    TopicExchange kdgExchange() {
        return ExchangeBuilder
                .topicExchange(EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    @Bean
    Queue orderPickedUpQueue() {
        return QueueBuilder.durable(ORDER_PICKEDUP_QUEUE).build();
    }

    @Bean
    Queue orderDeliveredQueue() {
        return QueueBuilder.durable(ORDER_DELIVERED_QUEUE).build();
    }

    @Bean
    Queue orderLocationQueue() {
        return QueueBuilder.durable(ORDER_LOCATION_QUEUE).build();
    }

    // Bindings (delivery publishes → restaurant consumes)
    @Bean
    Binding bindOrderPickedUpToExchange(TopicExchange kdgExchange) {
        return BindingBuilder.bind(orderPickedUpQueue())
                .to(kdgExchange)
                .with("delivery.*.order.pickedup.v1");
    }

    @Bean
    Binding bindOrderDeliveredToExchange(TopicExchange kdgExchange) {
        return BindingBuilder.bind(orderDeliveredQueue())
                .to(kdgExchange)
                .with("delivery.*.order.delivered.v1");
    }

    @Bean
    Binding bindOrderLocationToExchange(TopicExchange kdgExchange) {
        return BindingBuilder.bind(orderLocationQueue())
                .to(kdgExchange)
                .with("delivery.*.order.location.v1");
    }
}
