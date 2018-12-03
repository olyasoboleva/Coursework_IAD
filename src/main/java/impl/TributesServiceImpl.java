package impl;

import entity.Game;
import entity.Status;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusesRepository;
import repository.TributesRepository;
import repository.UserRepository;
import service.TributesService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Service("tributesService")
public class TributesServiceImpl implements TributesService {

    private final TributesRepository tributesRepository;
    private final UserRepository userRepository;
    private final StatusesRepository statusesRepository;

    @Autowired
    public TributesServiceImpl(TributesRepository tributesRepository, UserRepository userRepository, StatusesRepository statusesRepository) {
        this.tributesRepository = tributesRepository;
        this.userRepository = userRepository;
        this.statusesRepository = statusesRepository;
    }

    /**
     * It creates tribute after checking his age
     * @param tribute tribute
     * @return tribute if age is correct and null if incorrect
     */
    @Transactional
    @Override
    public Tribute createTribute(Tribute tribute) {
        User user = userRepository.findUserByTributesByUser(tribute);
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        Date diff =  new Date(date.getTime() - user.getBirthday().getTime());
        if (diff.getTime()/1000/60/60/24 < 12*365) {
            return null;
        }
        if (diff.getTime()/1000/60/60/24 > 18*365) {
            return null;
        }
        Status status = statusesRepository.findStatuseByName("Трибут");
        user.setStatus(status);
        userRepository.save(user);
        tributesRepository.save(tribute);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(Tribute tribute) {
        tributesRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public Tribute updateTribute(Tribute tribute) {
        tributesRepository.save(tribute);
        return tribute;
    }

    @Override
    public Tribute getTributeById(long tributeId) {
        return tributesRepository.findTributeByTributeId(tributeId);
    }

    @Override
    public List<Tribute> getTributesByUser(User user) {
        return tributesRepository.getTributesByUser(user);
    }

    @Override
    public List<Tribute> getTributesByGame(Game game) {
        return tributesRepository.getTributesByGame(game);
    }

    @Override
    public List<Tribute> getTributesByStatusAndGame(String status, Game game) {
        return tributesRepository.getTributesByStatusAndGame(status, game);
    }
}
