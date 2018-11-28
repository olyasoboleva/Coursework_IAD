package repository;

import entity.DistrictsEntity;
import entity.TributesEntity;
import entity.UserloginEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
    UsersEntity findUsersEntityByUserid(int userid);
    List<UsersEntity> getUsersEntitiesByDistrictsByDistrictAndSexAndBirthdayLessThanAndBirthdayGreaterThanAndStatus(DistrictsEntity district, boolean sex, Date birthday, Date birthday2, String status);
    UsersEntity findUsersEntityByTributesByUserid(TributesEntity tribute);
    UsersEntity findUsersEntityByUserlogin(UserloginEntity userlogin);
    List<UsersEntity> getUsersEntitiesByStatus(String status);
}
