

CREATE TABLE restaurant (
                                                 uuid UUID PRIMARY KEY,
                                                 owner_id UUID NOT NULL UNIQUE,              -- enforces 1:1
                                                 email TEXT NOT NULL,
                                                 picture_url TEXT,
                                                 default_prep_min INTEGER NOT NULL,
                                                 cuisine_type TEXT NOT NULL,
                                                 street TEXT NOT NULL,
                                                 house_number TEXT NOT NULL,
                                                 postal_code TEXT NOT NULL,
                                                 city TEXT NOT NULL,
                                                 country TEXT NOT NULL
);



CREATE TABLE owner (
                                                     uuid UUID PRIMARY KEY,
                                                     restaurant UUID NOT NULL
);