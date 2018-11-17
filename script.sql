CREATE TYPE type_of_location AS ENUM (
'Джунгли', 'Лес', 'Водная', 'Степь', 'Горы', 'Лесостепь', 'Снежная', 'Пустыня', 'Тундра', 'Тайга', 'Город'
);

CREATE TABLE arenas (
arenaID integer PRIMARY KEY,
arena_length smallint NOT NULL,
arena_width smallint NOT NULL,
typeOfLocation type_of_location NOT NULL,
CONSTRAINT check_area CHECK ((arena_width> 0) AND (arena_length> 0)) );

CREATE TYPE type_of_activity AS ENUM (
'Роскошь', 'Оружие', 'Добыча угля', 'Электроника', 'Энергетика', 'Рыболовство', 'Сельское хозяйство', 'Транспорт', 'Древесина', 'Текстиль', 'Животноводство', 'Ядерное оружие', 'Управление'
);

CREATE TABLE districts (
districtID smallint PRIMARY KEY,
name varchar(20) UNIQUE,
typeOfActivity type_of_activity NOT NULL,
skillID integer
);

CREATE TYPE type_of_hooks AS ENUM (
'Психологическая', 'Ядовитая', 'Природная', 'Боевая', 'Другая'
);

CREATE TABLE hooks (
hookID bigint PRIMARY KEY,
name varchar(64) NOT NULL,
typeOfHook type_of_hooks,
description text,
damage smallint NOT NULL CHECK (damage >= 0),
radiusOfAction integer NOT NULL CHECK (radiusOfAction >= 0)
);

CREATE TABLE prices (
priceID integer PRIMARY KEY,
name varchar(64) NOT NULL,
cost integer CHECK (cost >= 0)
);

CREATE TYPE type_of_presents AS ENUM (
'Еда', 'Оружие', 'Лекарства', 'Инструменты', 'Другое'
);

CREATE TABLE shop (
productID integer PRIMARY KEY,
name varchar(64) NOT NULL,
cost smallint NOT NULL CHECK (cost >= 0),
typeOfPresent type_of_presents NOT NULL,
description text,
healthRecovery smallint
CHECK ((healthRecovery < 100) AND (healthRecovery >= 0))
);

CREATE TYPE type_of_weapons AS ENUM (
'Метательное', 'Колющее', 'Режущее', 'Колюще-режущее', 'Ударно-дробящее', 'Взрывное', 'Защитное' 
);

CREATE TABLE weapons (
weaponID integer PRIMARY KEY,
name varchar(64) NOT NULL UNIQUE,
typeOfWeapon type_of_weapons NOT NULL,
damage smallint CHECK ((damage >= 0) AND (damage <= 100)),
radiusOfAction smallint CHECK (radiusOfAction > 0)
);

CREATE TABLE users (
userID integer PRIMARY KEY,
surname varchar(30) NOT NULL,
name varchar(30) NOT NULL,
nick varchar(30) NOT NULL UNIQUE,
height smallint,
weight smallint,
sex boolean NOT NULL,
district smallint REFERENCES districts,
birthday date,
password varchar(40) NOT NULL,
status varchar(40),
cash integer
CONSTRAINT info CHECK (height > 0 AND weight >0 AND cash >= 0)
);




 CREATE TABLE locationOfHooks (
locationID bigint PRIMARY KEY,
coordinateX smallint NOT NULL,
coordinateY smallint NOT NULL,
gameID integer,
hookID bigint REFERENCES hooks
);


CREATE TYPE type_of_skills AS ENUM (
'Спортивные', 'Боевые', 'Информационные', 'Другие'
);

CREATE TABLE skills (
skillID integer PRIMARY KEY,
name varchar(32) NOT NULL,
description text,
typeOfSkill type_of_skills NOT NULL,
weaponID integer REFERENCES weapons
);

CREATE TABLE trainings (
trainingID integer PRIMARY KEY,
skillID integer REFERENCES skills,
coefficient smallint CHECK (coefficient >= 0),
duration smallint CHECK (duration > 0),
description text,
trainer integer REFERENCES users
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
duration  interval NOT NULL
);

ALTER TABLE locationOfHooks ADD FOREIGN KEY (gameID) REFERENCES games;

CREATE TABLE tributes (
tributeID bigint PRIMARY KEY,
userID integer REFERENCES users,
gameID integer REFERENCES games NOT NULL,
status varchar(40),
causeOfDeath varchar(80),
killerID  bigint REFERENCES tributes,
weaponID integer REFERENCES weapons,
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

CREATE TABLE presentsAndLocation (
applyingID integer PRIMARY KEY,
productID integer REFERENCES shop NOT NULL,
typeOfLocation type_of_location NOT NULL
);

 CREATE TABLE hooksAndSkills (
hookID integer REFERENCES hooks NOT NULL,
skillID integer REFERENCES skills NOT NULL,
PRIMARY KEY (hookID, skillID)
);

CREATE TABLE hooksAndPresents (
hookID integer REFERENCES hooks NOT NULL,
productID integer REFERENCES shop NOT NULL,
PRIMARY KEY (hookID, productID)
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

