package impl;

import entity.StatusesEntity;
import entity.TributesEntity;
import entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusesRepository;
import repository.TributesRepository;
import repository.UserLoginRepository;
import repository.UserRepository;
import service.TributesService;

import java.sql.Date;
import java.util.Calendar;


@Service("tributesService")
public class TributesServiceImpl implements TributesService {

    private final TributesRepository tributesRepository;
    private final UserRepository userRepository;
    private final StatusesRepository statusesRepository;
    private final UserLoginRepository userLoginRepository;
    UsersServiceImpl usersService;

    @Autowired
    public TributesServiceImpl(TributesRepository tributesRepository, UserRepository userRepository, StatusesRepository statusesRepository, UserLoginRepository userLoginRepository) {
        this.tributesRepository = tributesRepository;
        this.userRepository = userRepository;
        this.statusesRepository = statusesRepository;
        usersService = new UsersServiceImpl(userRepository, userLoginRepository);
        this.userLoginRepository = userLoginRepository;
    }


    /**
     * It creates tribute after checking his age
     * @param tribute tribute
     * @return tribute if age is correct and null if incorrect
     */
    @Transactional
    @Override
    public TributesEntity createTribute(TributesEntity tribute) {
        UsersEntity user = userRepository.findUsersEntityByTributesByUser(tribute);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        Date diff =  new Date(date.getTime() - user.getBirthday().getTime());
        if (diff.getTime()/1000/60/60/24 < 12*365) {
            return null;
        }
        if (diff.getTime()/1000/60/60/24 > 18*365) {
            return null;
        }
        StatusesEntity status = statusesRepository.findStatusesEntityByName("Трибут");
        user.setStatus(status);
        user = usersService.updateUser(user);
        tributesRepository.save(tribute);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(TributesEntity tribute) {
        tributesRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public TributesEntity updateTribute(TributesEntity tribute) {
        tributesRepository.save(tribute);
        return tribute;
    }
}
