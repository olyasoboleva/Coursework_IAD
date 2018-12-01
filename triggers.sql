/* ///////////////////////////////////////////////// !!*/
CREATE FUNCTION check_date_of_birth() RETURNS trigger AS '
BEGIN
IF (((SELECT startDate FROM games WHERE gameId = NEW.gameId) - (SELECT birthday FROM users WHERE userId = NEW.userId)) < 12*365) THEN
	RAISE WARNING ''Трибут должен достичь 12 лет для участия в играх'';
	RETURN NULL;
	ELSEIF (((SELECT startDate FROM games WHERE gameId = NEW.gameId) - (SELECT birthday FROM users WHERE userId = NEW.userId)) > 18*365) THEN
		RAISE WARNING ''Трибут не может быть старше 18 лет'';
		RETURN NULL;
	ELSE
		RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_date_of_birth BEFORE INSERT OR UPDATE
ON tributes
FOR EACH ROW
EXECUTE PROCEDURE check_date_of_birth();

/* ///////////////////////////////////////////////// !!*/

CREATE FUNCTION check_number_of_weapons() RETURNS trigger AS '
BEGIN
IF ((SELECT COUNT(weaponId) FROM weaponsingame WHERE tributeId = NEW.tributeId GROUP BY tributeId) >= 3) THEN
	RAISE WARNING ''Один трибут не может иметь больше трёх оружий одновременно'';
	RETURN NULL;
ELSE
RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;


CREATE TRIGGER check_number_of_weapons BEFORE INSERT OR UPDATE ON weaponsingame
  FOR EACH ROW
EXECUTE PROCEDURE check_number_of_weapons();

/* ///////////////////////////////////////////////// */

CREATE FUNCTION check_present() RETURNS trigger AS '
BEGIN
IF ((SELECT userId FROM tributes WHERE tributes.tributeId = NEW.tributeId) = NEW.senderid) THEN
	RAISE WARNING ''Трибут и отправитель не могут быть одним человеком'';
	RETURN NULL;
	ELSEIF ((SELECT gameId FROM tributes WHERE ((userId = NEW.senderid)
		AND (gameId = (SELECT gameId FROM tributes WHERE tributes.tributeId = NEW.tributeId)))) IS NOT NULL)
	THEN
		RAISE WARNING ''Отправитель не может быть трибутом'';
		RETURN NULL;
		ELSEIF (((SELECT cash FROM users WHERE users.userId = NEW.senderid) - (SELECT cost FROM shop WHERE shop.productId = NEW.productId)*NEW.quantity ) < 0) THEN
			RAISE WARNING ''У отправителя не хватает денег на подарок'';
			RETURN NULL;
		ELSE
			UPDATE users SET cash = users.cash - (SELECT cost FROM shop WHERE shop.productId = NEW.productId)*NEW.quantity WHERE users.userId = NEW.senderid;
			RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_present BEFORE INSERT OR UPDATE
  ON presentstotributes
  FOR EACH ROW
EXECUTE PROCEDURE check_present();

/* ///////////////////////////////////////////////// it will be generated*/

CREATE FUNCTION check_tributes() RETURNS trigger AS '
BEGIN
IF ((SELECT typeOfGame FROM games WHERE games.gameId = NEW.gameId) = TRUE ) THEN
	IF (((SELECT COUNT(tributeId) FROM tributes WHERE gameId = NEW.gameId GROUP BY gameId) + 1) > 24) THEN
		RAISE WARNING ''В обычной версии игры не может быть больше 24 трибутов'';
		RETURN NULL;
	ELSEIF (((SELECT COUNT(tributes.tributeId) FROM tributes
		JOIN users USING(userId)
		WHERE (tributes.gameId = NEW.gameId) AND (users.sex = (SELECT sex FROM users WHERE users.userId=NEW.userId))
		GROUP BY tributes.gameId)  + 1) > 12) THEN
		RAISE WARNING ''В обычной версии игры не может быть больше 12 трибутов этого пола'';
		RETURN NULL;
			ELSEIF (((SELECT COUNT(tributeId) FROM tributes
				JOIN users ON ((users.userId = tributes.userId)
				AND (sex = (SELECT sex FROM users WHERE users.userId = NEW.userId))
				AND (district = (SELECT district FROM users WHERE users.userId = NEW.userId)))
				WHERE gameId = NEW.gameId
				GROUP BY gameId) + 1) >= 2) THEN
				RAISE WARNING ''Для обычной версии игры уже добавлен трибут этого пола из этого дистрикта'';
				RETURN NULL;
				ELSE
					RETURN NEW;
	END IF;
ELSEIF ((SELECT COUNT(tributeId) FROM tributes WHERE gameId = NEW.gameId GROUP BY gameId)
	>= (SELECT numberOfTributes FROM games WHERE gameId = NEW.gameId)) THEN
	RAISE WARNING ''В этой версии игры не может быть больше трибутов'';
	RETURN NULL;
ELSE
	RETURN NEW;

END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_tributes BEFORE INSERT
  ON tributes
  FOR EACH ROW
EXECUTE PROCEDURE check_tributes();


/* ///////////////////////////////////////////////// */

