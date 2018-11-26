import config.database.DatabaseConfig;
import entity.ArenasEntity;
import entity.DistrictsEntity;
import entity.SkillsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.ArenasRepository;
import repository.DistrictsRepository;
import repository.SkillsRepository;

import java.util.List;

public class Main {


    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);


        ArenasEntity arenasEntity = new ArenasEntity();
        arenasEntity.setArenaLength((short) 123);
        arenasEntity.setArenaWidth((short)12);
        arenasEntity.setTypeoflocation("Лес");

        ArenasRepository arenasRepository = (ArenasRepository) ctx.getBean("arenasRepository");

        arenasRepository.save(arenasEntity);


        DistrictsRepository districtsRepository = (DistrictsRepository) ctx.getBean("districtsRepository");
        SkillsRepository skillsRepository = (SkillsRepository) ctx.getBean("skillsRepository");


        DistrictsEntity districtsEntity = new DistrictsEntity();
        districtsEntity.setName("Дистрикт 5");
        districtsEntity.setTypeofactivity("Роскошь");

        SkillsEntity skillsEntity = new SkillsEntity();
        skillsEntity.setName("Изготовление украшений");
        skillsEntity.setTypeofskill("Другое");
        skillsEntity.setDescription("kek lol");

        skillsRepository.save(skillsEntity);
        districtsEntity.setSkillsBySkillid(skillsEntity);
        districtsRepository.save(districtsEntity);

        SkillsEntity skillsEntity1 = skillsRepository.findSkillsEntityByDistrictsBySkillid(districtsEntity);
        System.out.println(skillsEntity1.getDescription());
    }
}
