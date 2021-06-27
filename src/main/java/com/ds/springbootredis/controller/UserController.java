package com.ds.springbootredis.controller;
import com.ds.springbootredis.bean.User;
import com.ds.springbootredis.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private  final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    //add to the usersDataCache of users by user.id
    @Cacheable(value = "usersDataCache",key = "#id") //,unless = "#result.id>3"
    public User getUser(@PathVariable long id){
        logger.info(" >> UserController : /user/{} call : ",id);
        return userService.getUser(id);

    }

    @PostMapping("/user")
    public User create(@RequestBody User user){
        logger.info(" >> UserController : /user :  {}",user);
        return userService.create(user);
    }
    @GetMapping("/users")
     public List<User> getAll(){
        logger.info(" >> UserController : /users : ");

        return userService.getAll();
     }

     @PutMapping("/update")
     // update the cache when updating the user data by id
     @CachePut(value = "usersDataCache",key = "#user.id")
     public User updateUser(@RequestBody User user)
     {
         logger.info(" >> UserController : /update :  {}",user);
         return userService.update(user);
     }

     @DeleteMapping("/delete/{id}")
     // delete from  the cache when deleting  the user by id
     @CacheEvict(value = "usersDataCache", allEntries = false,key = "#id")
     public void deleteUser(@PathVariable Long id)
     {
          logger.info(" >> UserController : /delete : {}",id);
          userService.delete(id);
          logger.info(" << UserController : /delete : {}",id);

     }
}