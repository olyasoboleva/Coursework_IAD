package impl;

import entity.UserLogin;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserLoginRepository;
import service.UserLoginService;

@Service("userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginRepository userLoginRepository;

    @Autowired
    public UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        this.userLoginRepository = userLoginRepository;
    }

    @Transactional
    @Override
    public UserLogin createLogin(UserLogin login) {
        userLoginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public UserLogin updateLogin(UserLogin login) {
        userLoginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public boolean deleteSLogin(UserLogin login) {
        userLoginRepository.delete(login);
        return true;
    }

    @Override
    public UserLogin getUserLoginByUser(User user) {
        return userLoginRepository.findUserLoginByUser(user);
    }

    @Override
    public UserLogin getUserLoginByNick(String nick) {
        return userLoginRepository.findUserLoginByNick(nick);
    }

    @Override
    public UserLogin getUserLoginByNickAndPassword(String nick, String password) {
        return userLoginRepository.findUserLoginByNickAndPassword(nick, password);
    }
}
