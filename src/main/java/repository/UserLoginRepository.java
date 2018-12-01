package repository;

import entity.UserLoginEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserLoginRepository extends CrudRepository<UserLoginEntity, Integer> {
    /**
     * find login parameters of user
     *
     * @param user user
     * @return user login
     */
    UserLoginEntity findUserLoginEntityByUser(UsersEntity user);


    //TODO: в сервисы или убрать
    //TODO: хмм, чет появились сомнения насчет всей этой фигни
    //TODO: можно в сервисы на поиск UserEntity
    //UserLoginEntity findUserLoginEntityByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserLoginEntity findUserLoginEntityByNickAndPassword(String nick, String password);
}
