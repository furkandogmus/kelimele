package com.tahsin.kelimele.business.concretes;

import com.tahsin.kelimele.business.abstracts.UserService;

import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.core.utilities.results.SuccessDataResult;
import com.tahsin.kelimele.core.utilities.results.SuccessResult;
import com.tahsin.kelimele.dataAccess.abstracts.UserDao;
import com.tahsin.kelimele.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

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


}
