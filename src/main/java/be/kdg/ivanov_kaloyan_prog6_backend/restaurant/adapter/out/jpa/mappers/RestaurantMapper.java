package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

import be.kdg.ivanov_kaloyan_prog6_backend.common.dto.DeliveryInfoDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.common.events.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.in.dto.AddressDTO;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.embeddables.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.entities.*;
import be.kdg.ivanov_kaloyan_prog6_backend.restaurant.domain.*;
import org.mapstruct.*;
import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", imports = {RestaurantId.class, OwnerId.class})
public interface RestaurantMapper {

    @Mapping(target = "id", expression = "java(domain.getId().id())")
    @Mapping(target = "ownerId", expression = "java(domain.getOwnerId().id())")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "pictureUrl", source = "pictureURL")
    @Mapping(target = "defaultPrepMinutes", source = "defaultPrepTime")
    @Mapping(target = "cuisineType", source = "cuisineType")
    @Mapping(target = "isOpen", expression = "java(domain.isOpen())")
    @Mapping(target = "address", expression = "java(toJpaAddress(domain.getAddress()))")
    @Mapping(target = "openingHours", expression = "java(toJpaOpeningHours(domain.getOpeningHours()))")
    @Mapping(target = "events", ignore = true)
    RestaurantJpaEntity toJpa(Restaurant domain);

    @AfterMapping
    default void mergeEvents(@MappingTarget RestaurantJpaEntity target, Restaurant source) {
        if (target.getEvents() != null) {
            target.getEvents().clear();
            target.getEvents().addAll(toJpaEvents(source.getDomainEvents(), source.getId().id()));
        } else {
            target.setEvents(toJpaEvents(source.getDomainEvents(), source.getId().id()));
        }
    }

    @ObjectFactory
    default Restaurant createRestaurant(RestaurantJpaEntity jpa) {
        List<DomainEvent> eventStore = jpa.getEvents() != null
                ? toDomainEvents(jpa.getEvents(), jpa.getId())
                : new ArrayList<>();

        return new Restaurant(
                RestaurantId.of(jpa.getId()),
                OwnerId.of(jpa.getOwnerId()),
                toDomainAddress(jpa.getAddress()),
                jpa.getEmail(),
                jpa.getPictureUrl(),
                jpa.getDefaultPrepMinutes(),
                jpa.getCuisineType(),
                toDomainOpeningHours(jpa.getOpeningHours()),
                eventStore,
                jpa.isOpen()
        );
    }

    default Restaurant toDomain(RestaurantJpaEntity jpa) {
        return createRestaurant(jpa);
    }

    default List<RestaurantEventJpaEntity> toJpaEvents(List<DomainEvent> domainEvents, UUID restaurantId) {
        if (domainEvents == null || domainEvents.isEmpty()) return new ArrayList<>();
        return domainEvents.stream()
                .map(e -> toJpaEvent(e, restaurantId))
                .collect(Collectors.toList());
    }

    default RestaurantEventJpaEntity toJpaEvent(DomainEvent domainEvent, UUID restaurantId) {
        return switch (domainEvent) {
            case SaveRestaurantEvent e -> new RestaurantEventJpaEntity(
                    e.id(),
                    restaurantId,
                    null,
                    e.eventPit(),
                    e.eventCatalog().name(),
                    toJpaAddressFromDTO(e.address()),
                    e.email(),
                    e.pictureURL(),
                    e.defaultPrepTime(),
                    e.cuisineType()
            );
            case OrderAcceptedEvent e -> new RestaurantEventJpaEntity(
                    e.eventId(),
                    restaurantId,
                    e.orderId(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    e.pickupAddress() != null
                            ? toJpaAddressFromDeliveryInfoDTO(e.pickupAddress())
                            : null,
                    null,
                    null,
                    null,
                    null
            );
            case OrderRejectedEvent e -> new RestaurantEventJpaEntity(
                    e.id(),
                    restaurantId,
                    e.orderId(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    null,
                    null,
                    null,
                    null,
                    null
            );
            case OrderReadyForPickUpEvent e -> new RestaurantEventJpaEntity(
                    e.eventId(),
                    restaurantId,
                    e.orderId(),
                    e.eventPit(),
                    e.eventCatalog().name(),
                    null,
                    null,
                    null,
                    null,
                    null
            );

            case RestaurantOpenEvent e -> new RestaurantEventJpaEntity(
                    e.id(),
                    restaurantId,
                    null,
                    e.eventPit(),
                    e.eventCatalog().name(),
                    null,
                    null,
                    null,
                    null,
                    null);

            case RestaurantCloseEvent e -> new RestaurantEventJpaEntity(
                    e.id(),
                    restaurantId,
                    null,
                    e.eventPit(),
                    e.eventCatalog().name(),
                    null,
                    null,
                    null,
                    null,
                    null);

            default -> throw new IllegalArgumentException("Unknown restaurant event type: " + domainEvent.getClass().getSimpleName());
        };
    }

    default List<DomainEvent> toDomainEvents(List<RestaurantEventJpaEntity> jpaEvents, UUID restaurantId) {
        if (jpaEvents == null || jpaEvents.isEmpty()) return new ArrayList<>();
        return jpaEvents.stream()
                .map(e -> toDomainEvent(e, restaurantId))
                .collect(Collectors.toList());
    }

    default DomainEvent toDomainEvent(RestaurantEventJpaEntity e, UUID restaurantId) {
        return switch (EventCatalog.valueOf(e.getEventType())) {
            case RESTAURANT_SAVED -> new SaveRestaurantEvent(
                    toDomainAddress(e.getAddress()),
                    restaurantId,
                    e.getEmail(),
                    e.getPictureURL(),
                    e.getDefaultPrepTime(),
                    e.getCuisineType()
            );
            case ORDER_ACCEPTED -> new OrderAcceptedEvent(
                    restaurantId,
                    e.getOrderId(),
                    e.getAddress() != null
                            ? DeliveryInfoDTO.from(
                            e.getAddress().getStreet(),
                            e.getAddress().getNumber().toString(),
                            e.getAddress().getPostalCode(),
                            e.getAddress().getCity())
                            : null,
                    null
            );
            case ORDER_REJECTED -> new OrderRejectedEvent(e.getOrderId());
            case ORDER_READY -> new OrderReadyForPickUpEvent(
                    restaurantId,
                    e.getOrderId()
            );

            case RESTAURANT_OPENED -> new RestaurantOpenEvent(e.getRestaurantId());

            case RESTAURANT_CLOSED -> new RestaurantCloseEvent(e.getRestaurantId());

            default -> throw new IllegalArgumentException("Unknown restaurant event type: " + e.getEventType());
        };
    }

    default AddressEmbeddable toJpaAddress(Address address) {
        return new AddressEmbeddable(
                address.street(),
                address.number(),
                address.postalCode(),
                address.city(),
                address.country()
        );
    }

    default AddressEmbeddable toJpaAddressFromDTO(AddressDTO dto) {
        return new AddressEmbeddable(
                dto.street(),
                dto.number(),
                dto.postalCode(),
                dto.city(),
                dto.country()
        );
    }

    default AddressEmbeddable toJpaAddressFromDeliveryInfoDTO(DeliveryInfoDTO dto) {
        return new AddressEmbeddable(
                dto.street(),
                Integer.valueOf(dto.number()),
                dto.postalCode(),
                dto.city(),
                null
        );
    }

    default Address toDomainAddress(AddressEmbeddable emb) {
        return new Address(
                emb.getStreet(),
                emb.getNumber(),
                emb.getPostalCode(),
                emb.getCity(),
                emb.getCountry()
        );
    }

    default Map<DayOfWeek, DayScheduleEmbeddable> toJpaOpeningHours(OpeningHours domain) {
        return domain.openingHours().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new DayScheduleEmbeddable(e.getValue().start(), e.getValue().end())
                ));
    }

    default OpeningHours toDomainOpeningHours(Map<DayOfWeek, DayScheduleEmbeddable> jpaMap) {
        Map<DayOfWeek, DaySchedule> domainMap = jpaMap.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> new DaySchedule(e.getValue().getStart(), e.getValue().getEnd())
                ));
        return new OpeningHours(domainMap);
    }
}
