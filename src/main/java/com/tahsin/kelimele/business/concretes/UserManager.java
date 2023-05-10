package com.tahsin.kelimele.business.concretes;

import com.tahsin.kelimele.business.abstracts.UserService;

import com.tahsin.kelimele.core.utilities.results.*;
import com.tahsin.kelimele.dataAccess.abstracts.UserDao;
import com.tahsin.kelimele.entities.concretes.User;
import com.tahsin.kelimele.entities.concretes.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@EnableScheduling
@Service
public class UserManager implements UserService {
    private UserDao userDao;
    private LocalDateTime lastAccessTime;

    @Autowired
    public UserManager(UserDao userDao){
        super();
        this.userDao = userDao;
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.getUserByEmail(email),"Başarılı");
    }

    @Override
    public DataResult<User> getUserById(Long id) {
        return new SuccessDataResult<User>(this.userDao.getUserById(id),"Başarılı");
    }

    @Override
    public Result deleteUserById(Long id) {
        this.userDao.delete(this.userDao.getUserById(id));
        return new SuccessResult("Başarılı");
    }

    public Result deleteUser(User user){
        this.userDao.delete(user);
        return new SuccessResult("Başarılı");
    }

    @Override
    public Result deleteUserByEmail(String email) {
        this.userDao.delete(this.userDao.getUserByEmail(email));
        return new SuccessResult("Başarılı");
    }

    @Override
    public Result addUser(User user) {
            this.userDao.save(user);
            return new SuccessResult("Başarılı");
        }

    @Override
    public Result changeGameState(User user) {
        user.setIsPlayed(!user.getIsPlayed());
        return new SuccessResult("Başarılı");
    }
    @Override
    public Result changeGameState(User user, Boolean value) {
        user.setIsPlayed(value);
        return new SuccessResult("Başarılı");
    }

    @Override
    public Result changeAllStates() {
        List<User> users = this.userDao.findAll();
        for(int i=0;i<users.size();i++){
            users.get(i).setIsPlayed(Boolean.FALSE);
            this.userDao.save(users.get(i));
        }
        return new SuccessResult("Başarılı");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void runChangeAllStatesDaily() {
        changeAllStates();
    }

}
