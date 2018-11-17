package repository;

import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {
    UsersEntity findUsersEntityByUserid(int userid);

}
