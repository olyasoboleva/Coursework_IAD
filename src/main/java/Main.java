import config.database.DatabaseConfig;
import entity.ArenasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.ArenasRepository;

public class Main {


    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfig.class);


        ArenasEntity arenasEntity = new ArenasEntity();
        arenasEntity.setArenaid(1);
        arenasEntity.setArenaLength((short) 123);
        arenasEntity.setArenaWidth((short)12);

        ArenasRepository arenasRepository = (ArenasRepository) ctx.getBean("arenasRepository");

        arenasRepository.save(arenasEntity);


    }
}
