# Keep Dishes Going — Backend

> A production-quality food delivery platform API built with **Java 21**, **Spring Boot 3**, and **Hexagonal Architecture**. Demonstrates advanced backend engineering: Domain-Driven Design, event-driven communication via RabbitMQ, OAuth2/JWT security with Keycloak, and CQRS projections.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Engineering Highlights](#engineering-highlights)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [API Reference](#api-reference)
- [Domain Models](#domain-models)
- [Security](#security)
- [Event-Driven Communication](#event-driven-communication)
- [Getting Started](#getting-started)
- [Infrastructure](#infrastructure)
- [Project Structure](#project-structure)
- [Challenges & Accomplishments](#challenges--accomplishments)
- [Roadmap](#roadmap)

---

## Project Overview

Keep Dishes Going is a full-featured food delivery backend serving two types of users:

| User Type | Capabilities |
|-----------|-------------|
| **Restaurant Owners** | Create and manage their restaurant, build and publish menus via a safe draft system, control open/close status, manage dish stock in real time, and monitor incoming orders |
| **Customers** | Browse restaurants and live menus, manage a shopping basket, place orders, and track order status through to delivery |

The system keeps the owner management side and the customer-facing side in real-time sync through an event-driven architecture powered by RabbitMQ. When an owner closes a restaurant, marks a dish out of stock, or accepts an order, those changes propagate asynchronously to customer read-models within seconds.

---

## Engineering Highlights

- **Hexagonal Architecture (Ports & Adapters)** — Each bounded context is cleanly separated into domain, application, and adapter layers. Business logic never imports frameworks or infrastructure types.
- **Domain-Driven Design** — Rich aggregate roots (`Restaurant`, `Menu`, `Order`, `Basket`) encapsulate business rules and emit domain events. Value objects (`Address`, `OpeningHours`) express the ubiquitous language.
- **CQRS & Projections** — Dedicated read models (`DishProjection`, `RestaurantProjection`, `OrderProjection`) are maintained separately from the write side, enabling fast, optimised customer queries without polluting domain aggregates.
- **Partial Event Sourcing** — `Restaurant` and `Menu` state changes are persisted as immutable event records, creating an audit trail and enabling potential replay.
- **Spring Modulith** — Each bounded context is declared as an enforced module. Cross-module communication is explicit and tracked, laying the groundwork for future microservice extraction.
- **Draft-Based Dish Publishing** — Dishes are always edited as drafts first. Publishing to the live menu is an intentional, explicit action that cannot accidentally disrupt customers.
- **OAuth2 / JWT via Keycloak** — Industry-standard identity with role-based access control extracted directly from JWT claims. Stateless sessions only.
- **In-Memory Basket Store** — The basket aggregate is intentionally kept in memory (not persisted) to model its transient, session-scoped nature.

---

## Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Spring Boot Application                  │
│                                                             │
│  ┌────────────────────────┐  ┌────────────────────────────┐ │
│  │   restaurant context   │  │  orderManagement context   │ │
│  │                        │  │                            │ │
│  │  Domain                │  │  Domain                    │ │
│  │  ├── Restaurant        │  │  ├── Order (Aggregate)     │ │
│  │  ├── Menu              │  │  ├── Basket (in-memory)    │ │
│  │  ├── Dish              │  │  ├── DishProjection        │ │
│  │  ├── DishDraft         │  │  ├── RestaurantProjection  │ │
│  │  └── Owner             │  │  └── OrderProjection       │ │
│  │                        │  │                            │ │
│  │  Application           │◄─┤► Application               │ │
│  │  (Use Cases)           │  │  (Use Cases)               │ │
│  │                        │RMQ                            │ │
│  │  Adapters              │  │  Adapters                  │ │
│  │  ├── HTTP Controllers  │  │  ├── HTTP Controllers       │ │
│  │  └── JPA Repositories  │  │  ├── JPA Repositories      │ │
│  └────────────────────────┘  │  └── In-Memory Basket      │ │
│                              └────────────────────────────┘ │
│                                                             │
│  ┌─────────────┐  ┌─────────────┐  ┌────────────────────┐  │
│  │  PostgreSQL  │  │  RabbitMQ   │  │  Keycloak (OAuth2) │  │
│  └─────────────┘  └─────────────┘  └────────────────────┘  │
└─────────────────────────────────────────────────────────────┘
```

**Layers within each bounded context:**

| Layer | Responsibility |
|-------|---------------|
| **Domain** | Aggregates, entities, value objects, domain events, and business invariants |
| **Application (Core)** | Use case implementations that orchestrate domain logic |
| **Port (in)** | Interfaces exposing what the application accepts |
| **Port (out)** | Interfaces abstracting persistence and messaging dependencies |
| **Adapter (in)** | HTTP controllers — translates REST requests to use case calls |
| **Adapter (out)** | JPA repositories and RabbitMQ publishers — implements outbound ports |

---

## Tech Stack

| Category | Technology |
|----------|-----------|
| Language | Java 21 |
| Framework | Spring Boot 3.5.6 |
| Modularity | Spring Modulith 1.4.1 |
| Database | PostgreSQL 17.6 |
| ORM | JPA / Hibernate + MapStruct 1.6.3 |
| Message Broker | RabbitMQ 3.13.7 (AMQP) |
| Identity Provider | Keycloak 26.3 (OAuth2 / OIDC / JWT) |
| Build Tool | Gradle 8.14.3 |
| Containerisation | Docker Compose |
| Testing | JUnit 5, Mockito, Spring Test |

---

## API Reference

### Restaurant Management

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/restaurants` | Create a new restaurant | `owner` role |
| `GET` | `/api/owners/{ownerId}/restaurant` | Get the restaurant belonging to an owner | — |
| `PATCH` | `/api/owners/restaurants/{restaurantId}/opened` | Open the restaurant | `owner` role |
| `PATCH` | `/api/owners/restaurants/{restaurantId}/closed` | Close the restaurant | `owner` role |
| `POST` | `/api/owners` | Register an owner profile (auto-called on first login) | Authenticated |

### Dish Management (Owner)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/restaurant/{restaurantId}/menu/dishes` | List all dishes on the live menu | — |
| `POST` | `/api/restaurant/{restaurantId}/menu/dishes` | Publish a draft to the live menu | `owner` role |
| `PATCH` | `/api/restaurant/{restaurantId}/menu/dishes/published` | Publish a specific dish | `owner` role |
| `PATCH` | `/api/restaurant/{restaurantId}/menu/dishes/unpublished` | Unpublish a dish | `owner` role |
| `PATCH` | `/api/restaurant/{restaurantId}/menu/dishes/{dishId}` | Mark a dish as out of stock | `owner` role |
| `PATCH` | `/api/restaurant/{restaurantId}/menu/dishes/backInStock` | Mark a dish as back in stock | `owner` role |
| `DELETE` | `/api/restaurant/{restaurantId}/menu/dishes/{dishId}` | Delete a dish permanently | `owner` role |

### Dish Drafts

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/drafts` | Create a new dish draft | — |
| `GET` | `/api/restaurant/{restaurantId}/drafts` | List all drafts for a restaurant | `owner` role |
| `DELETE` | `/api/drafts/{draftId}` | Delete a draft without publishing | `owner` role |

### Basket (Customer)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/customer/baskets/basketLines` | Add a dish to the basket | — |
| `PATCH` | `/api/customer/baskets/{basketId}/basketLines/{dishId}` | Decrease item quantity (removes line at 0) | — |

### Orders (Customer)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `POST` | `/api/customer/baskets/{basketId}/orders` | Place an order from the basket | — |
| `GET` | `/api/customer/orders/{orderId}` | Retrieve order details and current status | — |

### Orders (Owner)

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| `GET` | `/api/owner/restaurant/{restaurantId}/activeOrders` | Get all active orders for a restaurant | `owner` role |
| `PATCH` | `/api/owner/orders/{orderId}` | Update order status (accept / reject / ready) | `owner` role |

### Customer Projections (Read Models)

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/restaurants` | List all restaurants |
| `GET` | `/api/customer/restaurants` | List all restaurant projections (customer view) |
| `GET` | `/api/dishes` | List all published dishes |
| `GET` | `/api/restaurant/{restaurantId}/dishes` | List published dishes for a specific restaurant |
| `GET` | `/api/enums` | Retrieve enum values (cuisine types, dish types, food tags) |

---

## Domain Models

### Restaurant Bounded Context

```
Restaurant (Aggregate Root)
├── id: UUID
├── ownerId: UUID
├── address: Address
│   └── { street, number, postalCode, city, country }
├── email: String
├── pictureURL: String
├── defaultPrepTime: Integer (minutes)
├── cuisineType: CuisineType
│   └── ITALIAN | BURGER | CHINESE | JAPANESE | MEXICAN | SUSHI | ...
├── openingHours: Map<DayOfWeek, DaySchedule { start: LocalTime, end: LocalTime }>
└── isOpen: Boolean

Menu (Aggregate Root)
├── id: UUID
├── restaurantId: UUID
└── dishes: Map<DishId, Dish>

Dish (Entity — within Menu)
├── name: String
├── type: DishType  — APPETIZER | MAIN_COURSE | DESSERT | BEVERAGE | SIDE
├── tags: List<FoodTag>  — VEGETARIAN | VEGAN | GLUTEN_FREE | CARNIVORE | ...
├── description: String
├── price: BigDecimal
├── pictureURL: String
├── state: PUBLISHED | UNPUBLISHED
└── stockStatus: IN_STOCK | OUT_OF_STOCK

DishDraft (Aggregate Root — safe working copy)
├── Same writable fields as Dish
└── dishId: UUID  (null until published; links draft → live dish)

Owner (Aggregate Root)
├── id: UUID
├── firstName, lastName, email: String
├── restaurantId: UUID (nullable until a restaurant is created)
└── createdAt: LocalDateTime
```

### Order Management Bounded Context

```
Order (Aggregate Root)
├── id: UUID
├── status: OrderStatus
│   └── PENDING → ACCEPTED → READY → PICKED_UP → DELIVERED
│       (can transition to REJECTED at any stage)
├── orderLines: List<OrderLine { name, quantity, price }>
├── customerInfo: CustomerInfo
│   └── { name, phoneNumber, deliveryAddress: { street, number, postalCode, city } }
├── total: BigDecimal
└── customerSessionId: UUID

Basket (Aggregate Root — intentionally in-memory, not persisted)
├── id: UUID
├── basketLines: Map<DishId, BasketLine { name, quantity, price }>
├── restaurantId: UUID
│   └── Invariant: all lines must belong to the same restaurant
└── customerSessionId: UUID
```

### Read Models (Projections)

| Projection | Updated by | Consumed by |
|------------|-----------|-------------|
| `DishProjection` | Restaurant context events | Customer menu browsing |
| `RestaurantProjection` | Restaurant context events | Customer restaurant listing |
| `OrderProjection` | Order context events | Owner order management dashboard |

---

## Security

Authentication and authorisation are handled by **Keycloak 26** acting as an OAuth2 / OIDC identity provider.

**Flow:**
1. The frontend authenticates the user via Keycloak and receives a signed JWT access token.
2. The JWT is sent as a `Bearer` token on every protected API request.
3. Spring Security validates the token signature against Keycloak's JWKS endpoint.
4. Roles are extracted from the `realm_access.roles` claim in the JWT payload via `KeycloakRealmRoleConverter`.

**Roles:**

| Role | What it unlocks |
|------|----------------|
| `owner` | Create/manage restaurants, manage dishes and drafts, view and update orders |
| *(unauthenticated)* | Browse restaurants and menus, manage basket, place and track orders |

Sessions are completely stateless — no server-side session is stored (`SessionCreationPolicy.STATELESS`).

**Key configuration (`application.properties`):**
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=
  http://localhost:8180/realms/keepdishesgoing
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=
  http://localhost:8180/realms/keepdishesgoing/protocol/openid-connect/certs
```

---

## Event-Driven Communication

The two bounded contexts communicate asynchronously via **RabbitMQ**, using Spring Modulith's AMQP event externaliser. Neither context imports code from the other — they are coupled only through shared event DTOs in the `common` module.

```
restaurant context                  RabbitMQ                  orderManagement context
──────────────────                  ────────                  ───────────────────────
RestaurantEventPublisher ──────────► restaurant.events ───► RestaurantProjection sync
MenuEventPublisher       ──────────► menu.events       ───► DishProjection sync

                                     order.events      ◄─── OrderPlacedEvent
                                                       ◄─── OrderStatusChangedEvent
                         ◄──────────                        (updates OrderProjection)
```

**Key domain events:**

| Event | Publisher | Subscriber | Effect |
|-------|-----------|------------|--------|
| `RestaurantCreatedEvent` | restaurant | orderManagement | Seed RestaurantProjection |
| `RestaurantStatusChangedEvent` | restaurant | orderManagement | Update open/close status on projection |
| `DishPublishedEvent` | restaurant | orderManagement | Create or update DishProjection |
| `DishStockChangedEvent` | restaurant | orderManagement | Update stock status on DishProjection |
| `OrderPlacedEvent` | orderManagement | restaurant | Create OrderProjection for the owner |
| `OrderStatusChangedEvent` | orderManagement | restaurant | Sync order status on OrderProjection |

---

## Getting Started

### Prerequisites

- **Java 21** JDK
- **Docker & Docker Compose**

### 1. Start Infrastructure

```bash
cd infrastructure
docker-compose up -d
```

This starts PostgreSQL on `:5444`, RabbitMQ on `:5672` (management UI `:15672`), and Keycloak on `:8180`.

Allow ~30 seconds for Keycloak to fully initialise before starting the app.

### 2. Run the Application

```bash
# Linux / macOS
./gradlew bootRun

# Windows
gradlew.bat bootRun
```

The API will be available at `http://localhost:8080`.

### 3. Verify

```bash
curl http://localhost:8080/api/enums
```

A JSON response with enum values confirms the application is running correctly.

### Default Service Credentials

| Service | URL | Username | Password |
|---------|-----|----------|----------|
| RabbitMQ Management | http://localhost:15672 | user | password |
| Keycloak Admin Console | http://localhost:8180/admin | admin | admin |
| PostgreSQL | localhost:5444/postgres | user | password |

---

## Infrastructure

All required infrastructure is defined in `infrastructure/docker-compose.yaml`:

| Service | Image | External Port | Purpose |
|---------|-------|--------------|---------|
| `app_postgres` | postgres:17.6-alpine | 5444 | Application database |
| `app_rabbitmq` | rabbitmq:3.13.7-management-alpine | 5672, 15672 | Message broker + management UI |
| `idp_keycloak` | keycloak/keycloak:26.3 | 8180 | OAuth2 / OIDC identity provider |
| `idp_mysql` | mysql:9.0.1 | 3307 | Keycloak's internal data store |

Two isolated Docker networks are used: `kdg-backend` for application services and `kdg-kc` for Keycloak infrastructure.

---

## Project Structure

```
src/main/java/
└── com/.../keepdishesgoing/
    ├── restaurant/                     # Restaurant bounded context
    │   ├── core/
    │   │   ├── domain/                 # Restaurant, Menu, Dish, DishDraft, Owner aggregates
    │   │   └── usecases/               # Business workflow implementations
    │   ├── adapter/
    │   │   ├── in/http/                # REST controllers
    │   │   └── out/jpa/                # JPA entities + repositories
    │   └── port/
    │       ├── in/                     # Inbound port interfaces (use case contracts)
    │       └── out/                    # Outbound port interfaces (persistence, events)
    │
    ├── orderManagement/                # Order bounded context
    │   ├── core/
    │   │   ├── domain/                 # Order, Basket aggregates + projection models
    │   │   └── usecases/
    │   ├── adapter/
    │   │   ├── in/http/                # Basket + order controllers
    │   │   └── out/                    # JPA repositories + in-memory basket store
    │   └── port/
    │
    ├── common/                         # Shared kernel
    │   ├── config/                     # RabbitMQ topology + Jackson configuration
    │   └── events/                     # Shared event DTOs used across contexts
    │
    └── security/                       # OAuth2 / JWT Spring Security configuration
        └── SecurityConfig.java

infrastructure/
└── docker-compose.yaml                 # PostgreSQL, RabbitMQ, Keycloak, MySQL
```

---

## Challenges & Accomplishments

### Challenges

- **MapStruct complexity** — Mapping between JPA entities and rich domain aggregates that carry domain events required carefully designed mappers to avoid circular dependencies and losing event state.
- **RabbitMQ transactional outbox** — Ensuring events are published reliably without dual-write problems across bounded context boundaries required integrating Spring Modulith's outbox mechanism and understanding AMQP delivery guarantees.
- **Maintaining DDD boundaries** — Keeping bounded contexts truly independent while still synchronising shared state (restaurant open/close, dish stock) through events rather than direct calls required careful event schema design.
- **Real-time projection consistency** — Determining exactly which events should update which projections, and ensuring no state is lost when events arrive out of order, was a significant design challenge.

### Accomplishments

- Implemented a **fully hexagonal, context-isolated backend** where no cross-context imports exist — only shared event contracts.
- Delivered **draft-based dish editing** so owners can prepare menu changes without touching the live, customer-visible menu.
- Achieved **real-time state synchronisation** across restaurant and order contexts with sub-second propagation via AMQP events.
- Applied **partial event sourcing** to `Restaurant` and `Menu` aggregates for a complete, replayable audit trail.
- Built **role-based access control** using industry-standard OAuth2/JWT with Keycloak, fully integrated into Spring Security.

---

## Roadmap

| Feature | Status |
|---------|--------|
| Sync open/close status | Done |
| Draft-based dish editing | Done |
| Order status tracking | Done |
| Real-time stock management | Done |
| Owner authentication | Done |
| Checkout with customer details | Done |
| Single-restaurant basket constraint | Done |
| Schedule batch publish/unpublish | Planned |
| Prevent checkout when restaurant is closed | Planned |
| Payment provider integration | Planned |
| Delivery time estimation (distance + prep time) | Planned |
| Restaurant filtering (cuisine, price, distance) | Planned |
| Automatic rejection of unaccepted orders | Planned |

---

## Related Repository

**Frontend:** [keep_dishes_going_frontend](../keep_dishes_going_frontend) — React 19 + TypeScript dual-role interface for customers and restaurant owners.
