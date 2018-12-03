package impl;

import entity.Game;
import entity.Status;
import entity.Tribute;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.StatusRepository;
import repository.TributeRepository;
import repository.UserRepository;
import service.TributeService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Service("tributeService")
public class TributeServiceImpl implements TributeService {

    private final TributeRepository tributeRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public TributeServiceImpl(TributeRepository tributeRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.tributeRepository = tributeRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
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
        Status status = statusRepository.findStatuseByName("Трибут");
        user.setStatus(status);
        userRepository.save(user);
        tributeRepository.save(tribute);
        return tribute;
    }

    @Transactional
    @Override
    public boolean deleteTribute(Tribute tribute) {
        tributeRepository.delete(tribute);
        return true;
    }

    @Transactional
    @Override
    public Tribute updateTribute(Tribute tribute) {
        tributeRepository.save(tribute);
        return tribute;
    }

    @Override
    public Tribute getTributeById(long tributeId) {
        return tributeRepository.findTributeByTributeId(tributeId);
    }

    @Override
    public List<Tribute> getTributesByUser(User user) {
        return tributeRepository.getTributesByUser(user);
    }

    @Override
    public List<Tribute> getTributesByGame(Game game) {
        return tributeRepository.getTributesByGame(game);
    }

    @Override
    public List<Tribute> getTributesByStatusAndGame(String status, Game game) {
        return tributeRepository.getTributesByStatusAndGame(status, game);
    }
}
