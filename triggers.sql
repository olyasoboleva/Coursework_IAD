/* ///////////////////////////////////////////////// !!*/
CREATE FUNCTION check_date_of_birth() RETURNS trigger AS '
BEGIN
IF (((SELECT start_date FROM game WHERE game_id = NEW.game_id) - (SELECT birthday FROM users WHERE user_id = NEW.user_id)) < 12*365) THEN
	RAISE WARNING ''Трибут должен достичь 12 лет для участия в играх'';
	RETURN NULL;
	ELSEIF (((SELECT start_date FROM game WHERE game_id = NEW.game_id) - (SELECT birthday FROM users WHERE user_id = NEW.user_id)) > 18*365) THEN
		RAISE WARNING ''Трибут не может быть старше 18 лет'';
		RETURN NULL;
	ELSE
		RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_date_of_birth BEFORE INSERT OR UPDATE
ON tribute
FOR EACH ROW
EXECUTE PROCEDURE check_date_of_birth();

/* ///////////////////////////////////////////////// !!*/

CREATE FUNCTION check_number_of_weapons() RETURNS trigger AS '
BEGIN
IF ((SELECT COUNT(weapon_id) FROM weapons_in_game WHERE tribute_id = NEW.tribute_id GROUP BY tribute_id) >= 3) THEN
	RAISE WARNING ''Один трибут не может иметь больше трёх оружий одновременно'';
	RETURN NULL;
ELSE
RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;


CREATE TRIGGER check_number_of_weapons BEFORE INSERT OR UPDATE ON weapons_in_game
  FOR EACH ROW
EXECUTE PROCEDURE check_number_of_weapons();

/* ///////////////////////////////////////////////// */

CREATE FUNCTION check_present() RETURNS trigger AS '
BEGIN
IF ((SELECT user_id FROM tribute WHERE tribute.tribute_id = NEW.tribute_id) = NEW.sender_id) THEN
	RAISE WARNING ''Трибут и отправитель не могут быть одним человеком'';
	RETURN NULL;
	ELSEIF ((SELECT game_id FROM tribute WHERE ((user_id = NEW.sender_id)
		AND (game_id = (SELECT game_id FROM tribute WHERE tribute.tribute_id = NEW.tribute_id)))) IS NOT NULL)
	THEN
		RAISE WARNING ''Отправитель не может быть трибутом'';
		RETURN NULL;
		ELSEIF (((SELECT cash FROM users WHERE users.user_id = NEW.sender_id) - (SELECT cost FROM shop WHERE shop.product_id = NEW.product_id)*NEW.quantity ) < 0) THEN
			RAISE WARNING ''У отправителя не хватает денег на подарок'';
			RETURN NULL;
		ELSE
			UPDATE users SET cash = users.cash - (SELECT cost FROM shop WHERE shop.product_id = NEW.product_id)*NEW.quantity WHERE users.user_id = NEW.sender_id;
			RETURN NEW;
END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_present BEFORE INSERT OR UPDATE
  ON presents_to_tribute
  FOR EACH ROW
EXECUTE PROCEDURE check_present();

/* ///////////////////////////////////////////////// it will be generated*/

CREATE FUNCTION check_tributes() RETURNS trigger AS '
BEGIN
IF ((SELECT type_of_game FROM game WHERE game.game_id = NEW.game_id) = TRUE ) THEN
	IF (((SELECT COUNT(tribute_id) FROM tribute WHERE game_id = NEW.game_id GROUP BY game_id) + 1) > 24) THEN
		RAISE WARNING ''В обычной версии игры не может быть больше 24 трибутов'';
		RETURN NULL;
	ELSEIF (((SELECT COUNT(tribute.tribute_id) FROM tribute
		JOIN users USING(user_id)
		WHERE (tribute.game_id = NEW.game_id) AND (users.sex = (SELECT sex FROM users WHERE users.user_id=NEW.user_id))
		GROUP BY tribute.game_id)  + 1) > 12) THEN
		RAISE WARNING ''В обычной версии игры не может быть больше 12 трибутов этого пола'';
		RETURN NULL;
			ELSEIF (((SELECT COUNT(tribute_id) FROM tribute
				JOIN users ON ((users.user_id = tribute.user_id)
				AND (sex = (SELECT sex FROM users WHERE users.user_id = NEW.user_id))
				AND (district = (SELECT district FROM users WHERE users.user_id = NEW.user_id)))
				WHERE game_id = NEW.game_id
				GROUP BY game_id) + 1) >= 2) THEN
				RAISE WARNING ''Для обычной версии игры уже добавлен трибут этого пола из этого дистрикта'';
				RETURN NULL;
				ELSE
					RETURN NEW;
	END IF;
ELSEIF ((SELECT COUNT(tribute_id) FROM tribute WHERE game_id = NEW.game_id GROUP BY game_id)
	>= (SELECT number_of_tributes FROM game WHERE game_id = NEW.game_id)) THEN
	RAISE WARNING ''В этой версии игры не может быть больше трибутов'';
	RETURN NULL;
ELSE
	RETURN NEW;

END IF;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER check_tributes BEFORE INSERT
  ON tribute
  FOR EACH ROW
EXECUTE PROCEDURE check_tributes();


/* ///////////////////////////////////////////////// */

