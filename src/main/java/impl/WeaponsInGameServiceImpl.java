package impl;

import entity.Game;
import entity.Tribute;
import entity.Weapon;
import entity.WeaponsInGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.WeaponsInGameRepository;
import repository.WeaponRepository;
import service.WeaponsInGameService;

import java.util.ArrayList;
import java.util.List;

@Service("weaponsInGameService")
public class WeaponsInGameServiceImpl implements WeaponsInGameService {

    private final WeaponsInGameRepository weaponsInGameRepository;
    private final WeaponRepository weaponRepository;

    @Autowired
    public WeaponsInGameServiceImpl(WeaponsInGameRepository weaponsInGameRepository, WeaponRepository weaponRepository) {
        this.weaponsInGameRepository = weaponsInGameRepository;

        this.weaponRepository = weaponRepository;
    }

    /**
     * It adds new weapon to tribute after checking its quantity
     * @param weaponInGame relation between tribute and weapon
     * @return adds relation if quantity is lower than 3 and null if not
     */
    @Transactional
    @Override
    public WeaponsInGame createWeaponsInGame(WeaponsInGame weaponInGame) {
        Tribute tribute = weaponInGame.getTribute();
        List<Weapon> list = weaponRepository.getWeaponByOwners(tribute);
        if (list.size() < 3 ) {
            return weaponsInGameRepository.save(weaponInGame);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public boolean deleteWeaponsInGame(WeaponsInGame weaponInGame) {
        weaponsInGameRepository.delete(weaponInGame);
        return true;
    }

    @Override
    public List<WeaponsInGame> getWeaponsInGameByTribute(Tribute tribute) {
        return weaponsInGameRepository.getWeaponsInGamesByTribute(tribute);
    }

    @Override
    public WeaponsInGame updateWeaponsInGame(WeaponsInGame weaponInGame) {
        return weaponsInGameRepository.save(weaponInGame);
    }

    @Override
    public List<WeaponsInGame> getWeaponsInGameWithoutOwner(Game game) {
        return weaponsInGameRepository.getWeaponsInGamesByGameAndTribute(game,null);
    }

    @Override
    public List<WeaponsInGame> throwWeaponsOnArena(Game game) {
        List<WeaponsInGame> weapons = new ArrayList<>();
        WeaponsInGame weaponsInGame;
        int numWeaponsToGenerate = (int)(Math.random()*77+24);
        int numWeaponsDB = (int)weaponRepository.count();
        if (numWeaponsDB!=0) {
            for (int i = 0; i < numWeaponsToGenerate; i++) {
                weaponsInGame = new WeaponsInGame(game, weaponRepository.findWeaponByWeaponId((int) (Math.random() * numWeaponsDB + 1)), (int) (Math.random() * game.getArena().getArenaLength()) + 1, (int) (Math.random() * game.getArena().getArenaWidth())+1);
                weapons.add(weaponsInGame);
                weaponsInGameRepository.save(weaponsInGame);
            }
        }
        return weapons;
    }

    @Override
    public List<WeaponsInGame> getWeaponsInGameInCellWithoutOwner(Game game, int x, int y) {
        return weaponsInGameRepository.getWeaponsInGamesByGameAndLocationXAndLocationYAndTribute(game, x, y, null);
    }

    @Override
    public List<WeaponsInGame> addFreeWeaponsToTribute(Tribute tribute) {
        List<WeaponsInGame> weaponsInGame = getWeaponsInGameInCellWithoutOwner(tribute.getGame(), tribute.getLocationX(), tribute.getLocationY());
        for (WeaponsInGame weapons: weaponsInGame){
            weapons.setTribute(tribute);
            weaponsInGameRepository.save(weapons);
        }
        return weaponsInGame;
    }

    @Override
    public WeaponsInGame getActiveWeapon(Tribute tribute) {
        return weaponsInGameRepository.getWeaponsInGameByTributeAndActive(tribute, true);
    }

    @Override
    public void setActiveWeapon(Tribute tribute, Weapon weapon) {
        WeaponsInGame weaponsInGamePast = getActiveWeapon(tribute);
        if (weaponsInGamePast!=null){
            weaponsInGamePast.setActive(false);
            weaponsInGameRepository.save(weaponsInGamePast);
        }
        WeaponsInGame weaponsInGameCur = weaponsInGameRepository.getWeaponsInGameByTributeAndWeapon(tribute, weapon);
        if (weaponsInGameCur!=null){
            weaponsInGameCur.setActive(true);
            weaponsInGameRepository.save(weaponsInGameCur);
        }
    }
}
