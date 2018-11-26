
CREATE TABLE arenas (
arenaID integer PRIMARY KEY,
arena_length smallint NOT NULL,
arena_width smallint NOT NULL,
typeOfLocation VARCHAR(40) NOT NULL,
CONSTRAINT check_area CHECK ((arena_width> 0) AND (arena_length> 0)) );


CREATE TABLE districts (
districtID smallint PRIMARY KEY,
name varchar(20) UNIQUE,
typeOfActivity VARCHAR(40) NOT NULL,
skillID integer
);

CREATE TABLE prices (
 priceID integer PRIMARY KEY,
 name varchar(64) NOT NULL,
 cost integer CHECK (cost >= 0)
);


CREATE TABLE shop (
productID integer PRIMARY KEY,
name varchar(64) NOT NULL,
cost smallint NOT NULL CHECK (cost >= 0),
typeOfPresent VARCHAR(40) NOT NULL,
description text,
healthRecovery smallint
CHECK ((healthRecovery < 100) AND (healthRecovery >= 0))
);

CREATE TABLE weapons (
weaponID integer PRIMARY KEY,
name varchar(64) NOT NULL UNIQUE,
typeOfWeapon VARCHAR(40) NOT NULL,
damage smallint CHECK ((damage >= 0) AND (damage <= 100)),
radiusOfAction smallint CHECK (radiusOfAction > 0)
);

CREATE TABLE users (
userID integer PRIMARY KEY,
login INTEGER REFERENCES userLogin,
surname varchar(30) NOT NULL,
name varchar(30) NOT NULL,
height smallint,
weight smallint,
sex boolean NOT NULL,
district smallint REFERENCES districts,
birthday date,
status varchar(40),
cash integer
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
coefficient smallint CHECK (coefficient >= 0),
duration smallint CHECK (duration > 0),
description text,
trainer integer REFERENCES users,
timeOfTraining TIME,
dayOfWeek smallint
);

CREATE TABLE userSkills (
 userID integer REFERENCES users,
 skillID integer REFERENCES skills,
 levelOfSkill smallint CHECK (levelOfSkill >= 0),
 PRIMARY KEY (userID, skillID)
);


CREATE TABLE games (
gameID integer PRIMARY KEY,
typeOfGame boolean NOT NULL,
steward integer REFERENCES users,
arena integer REFERENCES arenas,
numberOfTributes smallint CHECK (numberOfTributes > 0),
startDate date NOT NULL,
duration  integer NOT NULL
);

CREATE TABLE tributes (
tributeID bigint PRIMARY KEY,
userID integer REFERENCES users,
gameID integer REFERENCES games NOT NULL,
status varchar(40),
causeOfDeath varchar(80),
health smallint DEFAULT 100,
CONSTRAINT health CHECK (health >= 0),
CONSTRAINT user_on_game UNIQUE(gameID, userID)
);

CREATE TABLE  presentsToTributes (
 sendingID bigint PRIMARY KEY,
 productID integer REFERENCES shop NOT NULL,
 tributeID bigint REFERENCES tributes NOT NULL,
 senderID integer REFERENCES users NOT NULL,
 quantity smallint CHECK (quantity >= 0)
);

ALTER TABLE districts ADD FOREIGN KEY (skillID) REFERENCES skills;

CREATE TABLE productsAndLocation (
applyingID integer PRIMARY KEY,
productID integer REFERENCES shop NOT NULL,
typeOfLocation VARCHAR(40) NOT NULL
);

CREATE TABLE weaponsInGame (
 tributeID integer REFERENCES tributes,
 weaponID integer REFERENCES weapons,
 PRIMARY KEY (tributeID, weaponID)
);

CREATE TABLE statuses (
 statusID bigint PRIMARY KEY,
 name varchar(40),
 priceID integer REFERENCES prices
);

CREATE TABLE userLogin (
  loginID INTEGER PRIMARY KEY,
  nick varchar(30) NOT NULL UNIQUE,
  password varchar(40) NOT NULL
);