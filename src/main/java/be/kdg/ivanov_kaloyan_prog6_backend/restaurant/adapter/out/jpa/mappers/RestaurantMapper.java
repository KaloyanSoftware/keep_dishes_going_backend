package be.kdg.ivanov_kaloyan_prog6_backend.restaurant.adapter.out.jpa.mappers;

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
    @Mapping(target = "address", expression = "java(toJpaAddress(domain.getAddress()))")
    @Mapping(target = "openingHours", expression = "java(toJpaOpeningHours(domain.getOpeningHours()))")
    @Mapping(target = "events", ignore = true)
    RestaurantJpaEntity toJpa(Restaurant domain);

    @AfterMapping
    default void linkEvents(@MappingTarget RestaurantJpaEntity jpa, Restaurant domain) {
        if (domain.getDomainEvents() == null || domain.getDomainEvents().isEmpty()) {
            jpa.setEvents(new ArrayList<>());
            return;
        }

        List<RestaurantEventJpaEntity> jpaEvents = domain.getDomainEvents().stream()
                .map(event -> toJpaEvent(event, domain.getId().id()))
                .collect(Collectors.toList());

        jpa.setEvents(jpaEvents);
    }

    @ObjectFactory
    default Restaurant createRestaurant(RestaurantJpaEntity jpa) {
        List<DomainEvent> eventStore = jpa.getEvents() != null
                ? toDomainEvents(jpa.getEvents())
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
                eventStore
        );
    }

    default Restaurant toDomain(RestaurantJpaEntity jpa) {
        return createRestaurant(jpa);
    }

    default RestaurantEventJpaEntity toJpaEvent(DomainEvent domainEvent, UUID restaurantId) {
        return switch (domainEvent) {
            case SaveRestaurantEvent event -> new RestaurantEventJpaEntity(
                    event.id(),
                    restaurantId,
                    event.eventPit(),
                    event.eventCatalog().name(),
                    toJpaAddressFromDTO(event.address()),
                    event.email(),
                    event.pictureURL(),
                    event.defaultPrepTime(),
                    event.cuisineType()
            );
            default -> throw new IllegalArgumentException(
                    "Unknown restaurant event type: " + domainEvent.getClass()
            );
        };
    }

    default List<DomainEvent> toDomainEvents(List<RestaurantEventJpaEntity> jpaEvents) {
        if (jpaEvents == null || jpaEvents.isEmpty()) {
            return new ArrayList<>();
        }

        return jpaEvents.stream()
                .map(this::toDomainEvent)
                .collect(Collectors.toList());
    }

    default DomainEvent toDomainEvent(RestaurantEventJpaEntity jpaEvent) {
        return switch (EventCatalog.valueOf(jpaEvent.getEventType())) {
            case RESTAURANT_SAVED -> new SaveRestaurantEvent(
                    toDomainAddress(jpaEvent.getAddress()),
                    jpaEvent.getRestaurantId(),
                    jpaEvent.getEmail(),
                    jpaEvent.getPictureURL(),
                    jpaEvent.getDefaultPrepTime(),
                    jpaEvent.getCuisineType()
            );

            default -> throw new IllegalArgumentException("No restaurant event catalog: " + jpaEvent.getEventType());
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
