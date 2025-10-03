CREATE SCHEMA restaurant;

CREATE TABLE restaurant.owner (
                       uuid UUID PRIMARY KEY,
                       first_name TEXT NOT NULL,
                       last_name TEXT NOT NULL
);

CREATE TABLE restaurant.restaurant (
                            uuid UUID PRIMARY KEY,
                            owner_id UUID NOT NULL UNIQUE REFERENCES restaurant.owner(uuid),              -- enforces 1:1
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
                                 restaurant_id    UUID NOT NULL REFERENCES restaurant.restaurant(uuid) ON DELETE CASCADE,

                                 visibility       TEXT NOT NULL,

                                 live_name        TEXT,
                                 live_type        TEXT,
                                 live_description TEXT,
                                 live_price INTEGER,
                                 live_picture_url TEXT,

                                 draft_name        TEXT NOT NULL,
                                 draft_type        TEXT NOT NULL,
                                 draft_description TEXT,
                                 draft_price INTEGER NOT NULL,
                                 draft_picture_url TEXT,

                                 created_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
                                 updated_at        TIMESTAMPTZ NOT NULL DEFAULT now(),

                                 CONSTRAINT ck_live_price_nonneg  CHECK (live_price  IS NULL OR live_price >= 0),
                                 CONSTRAINT ck_draft_price_nonneg CHECK (draft_price >= 0)
);

-- Tags on the LIVE snapshot
CREATE TABLE restaurant.dish_live_tag (
                                          dish_id UUID NOT NULL REFERENCES restaurant.dish(uuid) ON DELETE CASCADE,
                                          tag     TEXT NOT NULL,
                                          PRIMARY KEY (dish_id, tag)
);

-- Tags on the DRAFT snapshot
CREATE TABLE restaurant.dish_draft_tag (
                                           dish_id UUID NOT NULL REFERENCES restaurant.dish(uuid) ON DELETE CASCADE,
                                           tag     TEXT NOT NULL,
                                           PRIMARY KEY (dish_id, tag)
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
