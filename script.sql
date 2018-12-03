CREATE TABLE location (
 locationID integer PRIMARY KEY ,
 name varchar(40),
 picture bytea not null
);

CREATE TABLE arena (
arenaID integer PRIMARY KEY,
arena_length integer NOT NULL,
arena_width integer NOT NULL,
locationId integer REFERENCES location NOT NULL,
CONSTRAINT check_area CHECK ((arena_width> 0) AND (arena_length> 0))
);


CREATE TABLE district (
districtID integer PRIMARY KEY,
name varchar(20) UNIQUE,
typeOfActivity VARCHAR(40) NOT NULL,
skillID integer
);

CREATE TABLE price (
 priceID integer PRIMARY KEY,
 name varchar(64) NOT NULL,
 cost integer CHECK (cost >= 0)
);

CREATE TABLE status (
 statusID integer PRIMARY KEY,
 name varchar(40),
 priceID integer REFERENCES price
);
CREATE TABLE shop (
productID integer PRIMARY KEY,
name varchar(64) NOT NULL,
cost integer NOT NULL CHECK (cost >= 0),
typeOfPresent VARCHAR(40) NOT NULL,
description text,
picture bytea not null ,
healthRecovery integer
CHECK ((healthRecovery < 100) AND (healthRecovery >= 0))
);

CREATE TABLE weapon (
weaponID integer PRIMARY KEY,
name varchar(64) NOT NULL UNIQUE,
typeOfWeapon VARCHAR(40) NOT NULL,
damage integer CHECK ((damage >= 0) AND (damage <= 100)),
radiusOfAction integer CHECK (radiusOfAction > 0),
picture bytea NOT NULL
);

CREATE TABLE userLogin (
 loginID INTEGER PRIMARY KEY,
 nick varchar(30) NOT NULL UNIQUE,
 password varchar(40) NOT NULL
);

CREATE TABLE users (
userID integer PRIMARY KEY,
loginId INTEGER REFERENCES userLogin,
surname varchar(30) NOT NULL,
name varchar(30) NOT NULL,
height integer,
weight integer,
sex boolean NOT NULL,
district integer REFERENCES district,
birthday date,
statusId integer REFERENCES status NOT NULL,
cash integer,
picture bytea NOT NULL,
CONSTRAINT info CHECK (height > 0 AND weight >0 AND cash >= 0)
);


CREATE TABLE skill (
skillID integer PRIMARY KEY,
name varchar(32) NOT NULL,
description text,
typeOfSkill VARCHAR(40) NOT NULL,
weaponID integer REFERENCES weapon
);

CREATE TABLE training (
trainingID integer PRIMARY KEY,
name VARCHAR(40),
skillID integer REFERENCES skill,
coefficient integer CHECK (coefficient >= 0),
duration integer CHECK (duration > 0),
description text,
trainer integer REFERENCES users,
timeOfTraining TIME,
dayOfWeek integer
);

CREATE TABLE userSkill (
 userSkillId INTEGER PRIMARY KEY ,
 userID integer REFERENCES users,
 skillID integer REFERENCES skill,
 levelOfSkill integer CHECK (levelOfSkill >= 0)
);


CREATE TABLE game (
gameID integer PRIMARY KEY,
typeOfGame boolean NOT NULL,
steward integer REFERENCES users,
arena integer REFERENCES arena,
numberOfTributes integer CHECK (numberOfTributes > 0),
startDate date NOT NULL,
duration  integer NOT NULL
);

CREATE TABLE tribute (
tributeID integer PRIMARY KEY,
userID integer REFERENCES users,
gameID integer REFERENCES game NOT NULL,
status varchar(40),
causeOfDeath varchar(80),
health integer DEFAULT 100,
CONSTRAINT health CHECK (health >= 0 and health <= 100),
CONSTRAINT user_on_game UNIQUE(gameID, userID)
);

CREATE TABLE  presentsToTribute (
 sendingID integer PRIMARY KEY,
 productID integer REFERENCES shop NOT NULL,
 tributeID integer REFERENCES tribute NOT NULL,
 senderID integer REFERENCES users NOT NULL,
 quantity integer CHECK (quantity >= 0)
);

ALTER TABLE district ADD FOREIGN KEY (skillID) REFERENCES skill;

CREATE TABLE productsAndLocation (
applyingID integer PRIMARY KEY,
productID integer REFERENCES shop NOT NULL,
locationId integer REFERENCES location NOT NULL
);

CREATE TABLE weaponsInGame (
 weaponInGameId INTEGER PRIMARY KEY ,
 tributeID integer REFERENCES tribute,
 weaponID integer REFERENCES weapon
);

CREATE TABLE hook (
 hookID integer PRIMARY KEY ,
 name varchar(40) NOT NULL ,
 locationId integer REFERENCES location NOT NULL ,
 damage integer NOT NULL
);

