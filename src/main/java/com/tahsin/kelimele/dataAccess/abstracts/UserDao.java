package com.tahsin.kelimele.dataAccess.abstracts;

import com.tahsin.kelimele.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    User getUserByEmail(String email);
    User getUserById(Long id);
}
