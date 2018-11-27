package repository;

import entity.UserloginEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserloginRepository extends CrudRepository<UserloginEntity, Integer> {
    UserloginEntity findUserloginEntityByUser(UsersEntity user);
    UserloginEntity findUserloginEntityByNick(String nick);
}
