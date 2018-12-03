import config.database.DatabaseConfig;
import entity.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);

        File imgPath = new File("pic.png");
        BufferedImage bufferedImage = ImageIO.read(imgPath);

        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        Location location = new Location("forest", data.getData());
        LocationRepository locationRepository = (LocationRepository)ctx.getBean("locationRepository");
        locationRepository.save(location);

        Arena arena = new Arena();
        arena.setArenaLength(123);
        arena.setArenaWidth(12);
        arena.setMainLocation(location);

        ArenaRepository arenaRepository = (ArenaRepository) ctx.getBean("arenaRepository");

        arenaRepository.save(arena);

////
        DistrictRepository districtRepository = (DistrictRepository) ctx.getBean("districtRepository");
        SkillRepository skillRepository = (SkillRepository) ctx.getBean("skillRepository");

        District district = new District();
        district.setName("Дистрикт 2");
        district.setTypeOfActivity("Роскошь");
///
        Skill skill = new Skill();
        skill.setName("Изготовление украшений");
        skill.setTypeOfSkill("Другое");
        skill.setDescription("kek lol");

        skillRepository.save(skill);
        district.setSkill(skill);
        districtRepository.save(district);

        Skill skill1 = skillRepository.findSkillByDistrict(district);
        System.out.println(skill1.getDescription());
/////////////////////////

        UserLogin userlogin = new UserLogin();
        userlogin.setNick("redish248");
        userlogin.setPassword("12345");
        UserLoginRepository userLoginRepository = (UserLoginRepository)ctx.getBean("userLoginRepository");
        userLoginRepository.save(userlogin);

        Status status = new Status();
        status.setName("Наблюдатель");
        StatusRepository statusRepository = (StatusRepository)ctx.getBean("statusRepository");
        statusRepository.save(status);

        User user = new User();
        user.setName("Ira");
        user.setSex(true);
        user.setSurname("Redkina");
        user.setCash(10000);
        user.setBirthday(new Date(1));
        user.setHeight(163);
        user.setWeight(47);
        user.setStatus(status);
        user.setPicture(data.getData());
        user.setUserLogin(userlogin);
        user.setDistrict(district);
        UserRepository userRepository = (UserRepository)ctx.getBean("userRepository");
        userRepository.save(user);

        Game game = new Game();
        GameRepository gameRepository = (GameRepository) ctx.getBean("gameRepository");
        game.setArena(arena);
        game.setDuration(15);
        game.setTypeOfGame(true);
        game.setStartDate(new Date(1));
        game.setNumberOfTributes(24);
        gameRepository.save(game);

        Tribute tribute = new Tribute();
        TributeRepository tributeRepository = (TributeRepository) ctx.getBean("tributeRepository");
        tribute.setUser(user);
        tribute.setGame(game);
        tributeRepository.save(tribute);


        Weapon weapon1 = new Weapon();
        weapon1.setName("меч");
        weapon1.setPicture(data.getData());
        weapon1.setTypeOfWeapon("Холодное оружие");
        weapon1.setRadiusOfAction(10);
        Weapon weapon2 = new Weapon();
        weapon2.setName("лук");
        weapon2.setPicture(data.getData());
        weapon2.setTypeOfWeapon("что-то там");
        weapon2.setRadiusOfAction(10);
        WeaponRepository weaponRepository = (WeaponRepository)ctx.getBean("weaponRepository");
        weaponRepository.save(weapon1);
        weaponRepository.save(weapon2);


        WeaponsInGame weaponsInGame1 = new WeaponsInGame();
        weaponsInGame1.setTribute(tribute);
        weaponsInGame1.setWeapon(weapon1);
        WeaponsInGame weaponsInGame2 = new WeaponsInGame();
        weaponsInGame2.setTribute(tribute);
        weaponsInGame2.setWeapon(weapon2);
        WeaponsInGameRepository weaponsInGameRepository = (WeaponsInGameRepository)ctx.getBean("weaponsInGameRepository");
        weaponsInGameRepository.save(weaponsInGame1);
        weaponsInGameRepository.save(weaponsInGame2);

        UserSkill userSkill = new UserSkill();
        userSkill.setSkill(skill);
        userSkill.setUser(user);
        userSkill.setLevelOfSkill(20);
        UserSkillRepository userSkillRepository = (UserSkillRepository)ctx.getBean("userSkillRepository");
        userSkillRepository.save(userSkill);

        Shop product = new Shop("Верёвка", 120, "Инструменты", "Прочная длинная веревка", 0, data.getData());
        ShopRepository shopRepository = (ShopRepository)ctx.getBean("shopRepository");
        shopRepository.save(product);

        ProductsAndLocation productsAndLocation = new ProductsAndLocation();
        productsAndLocation.setLocation(location);
        productsAndLocation.setProduct(product);
        ProductsAndLocationRepository productsAndLocationRepository = (ProductsAndLocationRepository)ctx.getBean("productsAndLocationRepository");
        productsAndLocationRepository.save(productsAndLocation);

        List<Weapon> weaponList = weaponRepository.getWeaponByOwners(tribute);
        for (Weapon weapon: weaponList){
            System.out.println(weapon.getName());
        }

        PresentsToTribute presentsToTribute = new PresentsToTribute();
        PresentsToTributeRepository presentsToTributeRepository = (PresentsToTributeRepository)ctx.getBean("presentsToTributeRepository");
        presentsToTribute.setQuantity(3);
        presentsToTribute.setSender(user);
        presentsToTribute.setProduct(product);
        presentsToTribute.setTribute(tribute);
        presentsToTributeRepository.save(presentsToTribute);

        List<Shop> shopList = shopRepository.findShopsByProductOwners(tribute);
        for (Shop prod: shopList){
            System.out.println(prod.getName());
        }

        Hook hook = new Hook("Пожар", 10, location);
        HookRepository hookRepository = (HookRepository)ctx.getBean("hookRepository");
        hookRepository.save(hook);

        TrainingRepository trainingRepository = (TrainingRepository)ctx.getBean("trainingRepository");
        Training training = new Training();
        LocalTime time = LocalTime.now();
        training.setTimeOfTraining(time);
        training.setDuration(100);
        trainingRepository.save(training);
    }
}