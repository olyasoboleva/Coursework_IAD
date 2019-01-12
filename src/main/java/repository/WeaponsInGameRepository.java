package repository;

import entity.Tribute;
import entity.WeaponsInGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponsInGameRepository extends CrudRepository<WeaponsInGame, Integer> {

    /**
     * Try to find all relations between tributes and theirs weapons
     * @param tribute tribute
     * @return links to tribute and his weapons
     */
    List<WeaponsInGame> getWeaponsInGamesByTribute(Tribute tribute);
}
