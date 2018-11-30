package repository;

import entity.UserloginEntity;
import entity.UsersEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserloginRepository extends CrudRepository<UserloginEntity, Integer> {
    /**
     * find login parameters of user
     *
     * @param user user
     * @return user login
     */
    UserloginEntity findUserloginEntityByUser(UsersEntity user);


    //TODO: в сервисы или убрать
    //TODO: хмм, чет появились сомнения насчет всей этой фигни
    //TODO: можно в сервисы на поиск UserEntity
    //UserloginEntity findUserloginEntityByNick(String nick);

    /**
     * find user by nick and password
     *
     * @param nick nick
     * @param password password
     * @return user login
     */
    UserloginEntity findUserloginEntityByNickAndPassword(String nick, String password);
}
