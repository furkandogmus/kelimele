package com.tahsin.kelimele.api.controllers;

import com.tahsin.kelimele.business.abstracts.UserService;
import com.tahsin.kelimele.core.utilities.results.DataResult;
import com.tahsin.kelimele.core.utilities.results.Result;
import com.tahsin.kelimele.core.utilities.results.SuccessDataResult;
import com.tahsin.kelimele.core.utilities.results.SuccessResult;
import com.tahsin.kelimele.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UsersController {
    private UserService userService;
    @Autowired
    public UsersController(UserService userService){
        super();
        this.userService = userService;
    }


    @GetMapping("/getUserById")
    public DataResult<User> getUserById(Long id){
        return new SuccessDataResult<User>(this.userService.getUserById(id).getData(),"Başarılı");
    }

    @GetMapping("/getUserByEmail")
    public DataResult<User> getUserByEmail(String email){
        return new SuccessDataResult<User>(this.userService.getUserByEmail(email).getData(),"Başarılı");
    }

    @DeleteMapping("/deleteUserByEmail")
    public Result deleteUserByEmail(@RequestBody String email){
        this.userService.deleteUserByEmail(email);
        return new SuccessResult("Başarılı");
    }


    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestBody User user){
        this.userService.deleteUser(user);
        return new SuccessResult("Başarılı");
    }


    @DeleteMapping("/deleteUserById")
    public Result deleteUserById(@RequestBody Long id){
        this.userService.deleteUserById(id);
        return new SuccessResult("Başarılı");
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        this.userService.addUser(user);
        return new SuccessResult("Başarılı");
    }

}
