import config.database.DatabaseConfig;
import entity.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;

import java.sql.Date;
import java.util.List;

public class Main {


    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);


        LocationsEntity locationsEntity = new LocationsEntity("forest", "forest.jpg");
        LocationsRepository locationsRepository = (LocationsRepository)ctx.getBean("locationsRepository");
        locationsRepository.save(locationsEntity);

        ArenasEntity arenasEntity = new ArenasEntity();
        arenasEntity.setArenaLength(123);
        arenasEntity.setArenaWidth(12);
        arenasEntity.setMainLocation(locationsEntity);

        ArenasRepository arenasRepository = (ArenasRepository) ctx.getBean("arenasRepository");

        arenasRepository.save(arenasEntity);

////
        DistrictsRepository districtsRepository = (DistrictsRepository) ctx.getBean("districtsRepository");
        SkillsRepository skillsRepository = (SkillsRepository) ctx.getBean("skillsRepository");

        DistrictsEntity districtsEntity = new DistrictsEntity();
        districtsEntity.setName("Дистрикт 55");
        districtsEntity.setTypeOfActivity("Роскошь");
///
        SkillsEntity skillsEntity = new SkillsEntity();
        skillsEntity.setName("Изготовление украшений");
        skillsEntity.setTypeOfSkill("Другое");
        skillsEntity.setDescription("kek lol");

        skillsRepository.save(skillsEntity);
        districtsEntity.setSkill(skillsEntity);
        districtsRepository.save(districtsEntity);

        SkillsEntity skillsEntity1 = skillsRepository.findSkillsEntityByDistrict(districtsEntity);
        System.out.println(skillsEntity1.getDescription());
/////////////////////////

        UserLoginEntity userlogin = new UserLoginEntity();
        userlogin.setNick("redish248");
        userlogin.setPassword("12345");
        UserLoginRepository userLoginRepository = (UserLoginRepository)ctx.getBean("userLoginRepository");
        userLoginRepository.save(userlogin);

        StatusesEntity status = new StatusesEntity();
        status.setName("Наблюдатель");
        StatusesRepository statusesRepository = (StatusesRepository)ctx.getBean("statusesRepository");
        statusesRepository.save(status);

        UsersEntity user = new UsersEntity();
        user.setName("Ira");
        user.setSex(true);
        user.setSurname("Redkina");
        user.setCash(10000);
        user.setBirthday(new Date(1));
        user.setHeight(163);
        user.setWeight(47);
        user.setStatus(status);
        user.setPicturePath("pic.jpg");
        user.setUserLogin(userlogin);
        user.setDistrict(districtsEntity);
        UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
        userRepository.save(user);

        GamesEntity game = new GamesEntity();
        GamesRepository gamesRepository = (GamesRepository) ctx.getBean("gamesRepository");
        game.setArena(arenasEntity);
        game.setDuration(15);
        game.setTypeOfGame(true);
        game.setStartDate(new Date(1));
        game.setNumberOfTributes(24);
        gamesRepository.save(game);

        TributesEntity tribute = new TributesEntity();
        TributesRepository tributesRepository = (TributesRepository) ctx.getBean("tributesRepository");
        tribute.setUser(user);
        tribute.setGame(game);
        tributesRepository.save(tribute);


        WeaponsEntity weapon1 = new WeaponsEntity();
        weapon1.setName("меч");
        weapon1.setPicturePath("/");
        weapon1.setTypeOfWeapon("Холодное оружие");
        weapon1.setRadiusOfAction(10);
        WeaponsEntity weapon2 = new WeaponsEntity();
        weapon2.setName("лук");
        weapon2.setPicturePath("/");
        weapon2.setTypeOfWeapon("что-то там");
        weapon2.setRadiusOfAction(10);
        WeaponsRepository weaponsRepository = (WeaponsRepository)ctx.getBean("weaponsRepository");
        weaponsRepository.save(weapon1);
        weaponsRepository.save(weapon2);


        WeaponsInGameEntity weaponsInGameEntity1 = new WeaponsInGameEntity();
        weaponsInGameEntity1.setTribute(tribute);
        weaponsInGameEntity1.setWeapon(weapon1);
        WeaponsInGameEntity weaponsInGameEntity2 = new WeaponsInGameEntity();
        weaponsInGameEntity2.setTribute(tribute);
        weaponsInGameEntity2.setWeapon(weapon2);
        WeaponsInGameRepository weaponsInGameRepository = (WeaponsInGameRepository)ctx.getBean("weaponsInGameRepository");
        weaponsInGameRepository.save(weaponsInGameEntity1);
        weaponsInGameRepository.save(weaponsInGameEntity2);

        UserSkillsEntity userSkillsEntity = new UserSkillsEntity();
        userSkillsEntity.setSkill(skillsEntity);
        userSkillsEntity.setUser(user);
        userSkillsEntity.setLevelOfSkill(20);
        UserSkillsRepository userSkillsRepository = (UserSkillsRepository)ctx.getBean("userSkillsRepository");
        userSkillsRepository.save(userSkillsEntity);

        ShopEntity product = new ShopEntity("Верёвка", 120, "Инструменты", "Прочная длинная веревка", 0, "rope.jpg");
        ShopRepository shopRepository = (ShopRepository)ctx.getBean("shopRepository");
        shopRepository.save(product);

        ProductsAndLocationEntity productsAndLocationEntity = new ProductsAndLocationEntity();
        productsAndLocationEntity.setLocation(locationsEntity);
        productsAndLocationEntity.setProduct(product);
        ProductsAndLocationRepository productsAndLocationRepository = (ProductsAndLocationRepository)ctx.getBean("productsAndLocationRepository");
        productsAndLocationRepository.save(productsAndLocationEntity);

        List<WeaponsEntity> weaponList = weaponsRepository.getWeaponsEntitiesByOwners(tribute);
        for (WeaponsEntity weapon: weaponList){
            System.out.println(weapon.getName());
        }
    }
}