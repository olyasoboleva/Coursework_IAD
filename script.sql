CREATE TABLE location (
  location_id serial PRIMARY KEY,
  name        varchar(40),
  picture     bytea not null
);

CREATE TABLE arena (
  arena_id     serial PRIMARY KEY,
  arena_length integer                     NOT NULL,
  arena_width  integer                     NOT NULL,
  location_id  integer REFERENCES location NOT NULL,
  CONSTRAINT check_area CHECK ((arena_width > 0) AND (arena_length > 0))
);


CREATE TABLE district (
  district_id      serial PRIMARY KEY,
  name             varchar(20) UNIQUE,
  type_of_activity VARCHAR(40) NOT NULL,
  skill_id         integer
);

CREATE TABLE price (
  price_id serial PRIMARY KEY,
  name     varchar(64) NOT NULL,
  cost     integer CHECK (cost >= 0)
);

CREATE TABLE status (
  status_id serial PRIMARY KEY,
  name      varchar(40),
  price_id  integer REFERENCES price
);
CREATE TABLE shop (
  product_id      serial PRIMARY KEY,
  name            varchar(64) NOT NULL,
  cost            integer     NOT NULL CHECK (cost >= 0),
  type_of_present VARCHAR(40) NOT NULL,
  description     text,
  picture         bytea       not null,
  health_recovery integer
    CHECK ((health_recovery < 100) AND (health_recovery >= 0))
);

CREATE TABLE weapon (
  weapon_id        serial PRIMARY KEY,
  name             varchar(64) NOT NULL UNIQUE,
  type_of_weapon   VARCHAR(40) NOT NULL,
  damage           integer CHECK ((damage >= 0) AND (damage <= 100)),
  radius_of_action integer CHECK (radius_of_action > 0),
  picture          bytea       NOT NULL
);

CREATE TABLE users (
  user_id       serial PRIMARY KEY,
  nick          varchar(30)               NOT NULL UNIQUE,
  password      varchar(128)              NOT NULL,
  surname       varchar(30)               NOT NULL,
  name          varchar(30)               NOT NULL,
  height        integer,
  weight        integer,
  sex           boolean                   NOT NULL,
  district      integer REFERENCES district,
  birthday      date,
  last_activity date,
  status_id     integer REFERENCES status NOT NULL,
  cash          integer,
  picture       bytea                     NOT NULL,
  CONSTRAINT info CHECK (height > 0 AND weight > 0 AND cash >= 0)
);


CREATE TABLE skill (
  skill_id      serial PRIMARY KEY,
  name          varchar(32) NOT NULL,
  description   text,
  type_of_skill VARCHAR(40) NOT NULL,
  weapon_id     integer REFERENCES weapon
);

CREATE TABLE training (
  training_id serial PRIMARY KEY,
  name        VARCHAR(40),
  skill_id    integer REFERENCES skill,
  coefficient integer CHECK (coefficient >= 0),
  duration    integer CHECK (duration > 0),
  description text,
  cost        integer NOT NULL CHECK (cost > 0),
  day_of_week integer
);

CREATE TABLE user_skill (
  user_skill_id  serial PRIMARY KEY,
  user_id        integer REFERENCES users,
  skill_id       integer REFERENCES skill,
  level_of_skill integer CHECK (level_of_skill >= 0)
);


CREATE TABLE game (
  game_id            serial PRIMARY KEY,
  type_of_game       boolean NOT NULL,
  steward            integer REFERENCES users,
  arena              integer REFERENCES arena,
  number_of_tributes integer CHECK (number_of_tributes > 0),
  start_date         date    NOT NULL,
  duration           integer NOT NULL,
  status             varchar(32) default ('created')
);

CREATE TABLE tribute (
  tribute_id     serial PRIMARY KEY,
  user_id        integer REFERENCES users,
  game_id        integer REFERENCES game NOT NULL,
  status         varchar(40),
  cause_of_death varchar(80),
  health         integer,
  hunger         integer,
  thirst         integer,
  location_x     integer,
  location_y     integer,
  CONSTRAINT user_on_game UNIQUE (game_id, user_id)
);

CREATE TABLE presents_to_tribute (
  sending_id serial PRIMARY KEY,
  product_id integer REFERENCES shop    NOT NULL,
  tribute_id integer REFERENCES tribute NOT NULL,
  sender_id  integer REFERENCES users   NOT NULL,
  quantity   integer CHECK (quantity >= 0)
);

ALTER TABLE district
  ADD FOREIGN KEY (skill_id) REFERENCES skill;

CREATE TABLE products_and_location (
  applying_id serial PRIMARY KEY,
  product_id  integer REFERENCES shop     NOT NULL,
  location_id integer REFERENCES location NOT NULL
);

CREATE TABLE weapons_in_game (
  weapon_in_game_id serial PRIMARY KEY,
  tribute_id        integer REFERENCES tribute,
  weapon_id         integer REFERENCES weapon
);

CREATE TABLE hook (
  hook_id     serial PRIMARY KEY,
  name        varchar(40)                 NOT NULL,
  location_id integer REFERENCES location NOT NULL,
  damage      integer                     NOT NULL,
  radius      integer                     not null
);

CREATE TABLE map (
  cell_id      serial PRIMARY KEY,
  arena_id     integer,
  x_coordinate integer check (x_coordinate >= 0),
  y_coordinate integer check (y_coordinate >= 0),
  location_id  integer,
  CONSTRAINT unique_coordinates UNIQUE (arena_id, x_coordinate, y_coordinate)
);

