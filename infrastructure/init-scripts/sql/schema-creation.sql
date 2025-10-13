CREATE SCHEMA restaurant;

CREATE SCHEMA order_management;

CREATE TABLE restaurant.owner (
                       uuid UUID PRIMARY KEY,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL
);

CREATE TABLE restaurant.restaurant (
                            uuid UUID PRIMARY KEY,
                            owner_id UUID NOT NULL UNIQUE REFERENCES restaurant.owner(uuid),
                            email TEXT NOT NULL,
                            picture_url TEXT,
                            default_prep_min INTEGER NOT NULL,
                            cuisine_type TEXT NOT NULL,
                            street TEXT NOT NULL,
                            house_number INTEGER NOT NULL,
                            postal_code TEXT NOT NULL,
                            city TEXT NOT NULL,
                            country TEXT NOT NULL
);


CREATE TABLE restaurant.menu (
                                       uuid UUID PRIMARY KEY,
                                       restaurant_id UUID NOT NULL UNIQUE REFERENCES restaurant.restaurant(uuid)
);



CREATE TABLE restaurant.opening_hours (
                               restaurant_id UUID NOT NULL REFERENCES restaurant.restaurant(uuid) ON DELETE CASCADE,
                               day_of_week   TEXT NOT NULL,
                               start_time    TIME NOT NULL,
                               end_time      TIME NOT NULL,
                               PRIMARY KEY (restaurant_id, day_of_week),
                               CONSTRAINT ck_time_range CHECK (start_time < end_time)
);

CREATE TABLE restaurant.dish (
                                 uuid             UUID PRIMARY KEY,
                                 menu_id          UUID NOT NULL REFERENCES restaurant.menu(uuid) ON DELETE CASCADE,
                                 visibility       TEXT NOT NULL,
                                 name             TEXT,
                                 type             TEXT,
                                 description      TEXT,
                                 price            FLOAT,
                                 picture_url      TEXT
);

CREATE TABLE restaurant.dish_draft (
                                 uuid             UUID PRIMARY KEY,
                                 restaurant_id    UUID NOT NULL REFERENCES restaurant.restaurant(uuid) ON DELETE CASCADE,
                                 dish_id          UUID REFERENCES restaurant.dish(uuid),
                                 name             TEXT,
                                 type             TEXT,
                                 description      TEXT,
                                 price            FLOAT,
                                 picture_url      TEXT
);

CREATE TABLE restaurant.dish_tag (
                                          dish_id UUID NOT NULL REFERENCES restaurant.dish(uuid) ON DELETE CASCADE,
                                          tag     TEXT NOT NULL,
                                          PRIMARY KEY (dish_id, tag)
);

CREATE TABLE restaurant.draft_tag (
                                           draft_id UUID NOT NULL REFERENCES restaurant.dish_draft(uuid) ON DELETE CASCADE,
                                           draft_tag     TEXT NOT NULL,
                                           PRIMARY KEY (draft_id, draft_tag)
);


CREATE TABLE order_management.item_availability (
                                 dish_id UUID PRIMARY KEY NOT NULL,
                                 restaurant_id UUID NOT NULL,
                                 published BOOLEAN NOT NULL,
                                 in_stock BOOLEAN NOT NULL
);






CREATE TABLE IF NOT EXISTS event_publication
(
    id               UUID PRIMARY KEY,
    listener_id      TEXT NOT NULL,
    event_type       TEXT NOT NULL,
    serialized_event TEXT NOT NULL,
    publication_date TIMESTAMPTZ NOT NULL,
    completion_date  TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS event_publication_serialized_event_hash_idx
    ON event_publication USING hash(serialized_event);

CREATE INDEX IF NOT EXISTS event_publication_by_completion_date_idx
    ON event_publication (completion_date);
