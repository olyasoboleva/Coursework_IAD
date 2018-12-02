CREATE TABLE locations (
 locationID integer PRIMARY KEY ,
 name varchar(40),
 picturePath varchar(40) not null
);

CREATE TABLE arenas (
arenaID integer PRIMARY KEY,
arena_length integer NOT NULL,
arena_width integer NOT NULL,
locationId integer REFERENCES locations NOT NULL,
CONSTRAINT check_area CHECK ((arena_width> 0) AND (arena_length> 0))
);


CREATE TABLE districts (
districtID integer PRIMARY KEY,
name varchar(20) UNIQUE,
typeOfActivity VARCHAR(40) NOT NULL,
skillID integer
);

CREATE TABLE prices (
 priceID integer PRIMARY KEY,
 name varchar(64) NOT NULL,
 cost integer CHECK (cost >= 0)
);

CREATE TABLE statuses (
 statusID integer PRIMARY KEY,
 name varchar(40),
 priceID integer REFERENCES prices
);
CREATE TABLE shop (
productID integer PRIMARY KEY,
name varchar(64) NOT NULL,
cost integer NOT NULL CHECK (cost >= 0),
typeOfPresent VARCHAR(40) NOT NULL,
description text,
picturepath  varchar(40) not null ,
healthRecovery integer
CHECK ((healthRecovery < 100) AND (healthRecovery >= 0))
);

CREATE TABLE weapons (
weaponID integer PRIMARY KEY,
name varchar(64) NOT NULL UNIQUE,
typeOfWeapon VARCHAR(40) NOT NULL,
damage integer CHECK ((damage >= 0) AND (damage <= 100)),
radiusOfAction integer CHECK (radiusOfAction > 0),
picturePath VARCHAR(40) NOT NULL
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
district integer REFERENCES districts,
birthday date,
statusId integer REFERENCES statuses NOT NULL,
cash integer,
picturePath VARCHAR(40) NOT NULL,
CONSTRAINT info CHECK (height > 0 AND weight >0 AND cash >= 0)
);


CREATE TABLE skills (
skillID integer PRIMARY KEY,
name varchar(32) NOT NULL,
description text,
typeOfSkill VARCHAR(40) NOT NULL,
weaponID integer REFERENCES weapons
);

CREATE TABLE trainings (
trainingID integer PRIMARY KEY,
name VARCHAR(40),
skillID integer REFERENCES skills,
coefficient integer CHECK (coefficient >= 0),
duration integer CHECK (duration > 0),
description text,
trainer integer REFERENCES users,
timeOfTraining TIME,
dayOfWeek integer
);

CREATE TABLE userSkills (
 userID integer REFERENCES users,
 skillID integer REFERENCES skills,
 levelOfSkill integer CHECK (levelOfSkill >= 0),
 PRIMARY KEY (userID, skillID)
);


CREATE TABLE games (
gameID integer PRIMARY KEY,
typeOfGame boolean NOT NULL,
steward integer REFERENCES users,
arena integer REFERENCES arenas,
numberOfTributes integer CHECK (numberOfTributes > 0),
startDate date NOT NULL,
duration  integer NOT NULL
);

CREATE TABLE tributes (
tributeID integer PRIMARY KEY,
userID integer REFERENCES users,
gameID integer REFERENCES games NOT NULL,
status varchar(40),
causeOfDeath varchar(80),
health integer DEFAULT 100,
CONSTRAINT health CHECK (health >= 0 and health <= 100),
CONSTRAINT user_on_game UNIQUE(gameID, userID)
);

CREATE TABLE  presentsToTributes (
 sendingID integer PRIMARY KEY,
 productID integer REFERENCES shop NOT NULL,
 tributeID integer REFERENCES tributes NOT NULL,
 senderID integer REFERENCES users NOT NULL,
 quantity integer CHECK (quantity >= 0)
);

ALTER TABLE districts ADD FOREIGN KEY (skillID) REFERENCES skills;

CREATE TABLE productsAndLocation (
applyingID integer PRIMARY KEY,
productID integer REFERENCES shop NOT NULL,
locationId integer REFERENCES locations NOT NULL
);

CREATE TABLE weaponsInGame (
 tributeID integer REFERENCES tributes,
 weaponID integer REFERENCES weapons,
 PRIMARY KEY (tributeID, weaponID)
);

CREATE TABLE hooks (
 hookID integer PRIMARY KEY ,
 name varchar(40) NOT NULL ,
 locationId integer REFERENCES locations NOT NULL ,
 damage integer NOT NULL
);

