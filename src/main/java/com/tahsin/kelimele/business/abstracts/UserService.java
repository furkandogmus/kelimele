package com.tahsin.kelimele.business.abstracts;

import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.entities.concretes.User;

public interface UserService {
    DataResult<User> getUserByEmail(String email);
    DataResult<User> getUserById(Long id);
    Result deleteUserById(Long id);
    Result deleteUser(User user);
    Result deleteUserByEmail(String email);
    Result addUser(User user);
    Result changeGameState(User user);
    Result changeGameState(User user, Boolean value);
    Result changeAllStates();

}
