package impl;

import entity.UserLoginEntity;
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
    public UserLoginEntity createLogin(UserLoginEntity login) {
        userLoginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public UserLoginEntity updateLogin(UserLoginEntity login) {
        userLoginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public boolean deleteSLogin(UserLoginEntity login) {
        userLoginRepository.delete(login);
        return true;
    }
}
