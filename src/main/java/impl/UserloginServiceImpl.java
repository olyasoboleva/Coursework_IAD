package impl;

import entity.UserloginEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.UserloginRepository;
import service.UserloginService;

@Service("userLoginService")
public class UserloginServiceImpl implements UserloginService{

    private final UserloginRepository userloginRepository;

    @Autowired
    public UserloginServiceImpl(UserloginRepository userloginRepository) {
        this.userloginRepository = userloginRepository;
    }

    @Transactional
    @Override
    public UserloginEntity createLogin(UserloginEntity login) {
        userloginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public UserloginEntity updateLogin(UserloginEntity login) {
        userloginRepository.save(login);
        return login;
    }

    @Transactional
    @Override
    public boolean deleteSLogin(UserloginEntity login) {
        userloginRepository.delete(login);
        return true;
    }
}
