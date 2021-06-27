package com.ds.springbootredis.service;

import com.ds.springbootredis.bean.User;
import com.ds.springbootredis.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private  final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(long id) {
        logger.info(" >> UserService : Entering getUser");
        Optional<User> userOp =  userRepository.findById(id);

        if(userOp.isPresent()){
            logger.info(" << UserService : Exiting getUser");
            return userOp.get();
        }
        else{
            logger.info(" << UserService : No Such User Exists : Exiting getUser");
            return null;
        }
    }


    public List<User> getAll() {
        logger.info(" >> UserService : Entering getAll");
        logger.info(" << UserService : Exiting getAll");
        return userRepository.findAll();

    }


    public void delete(long id) {
        userRepository.deleteById(id);
    }


    public User update(User user) {
        logger.info(">> UserService : Entering update");
        long id = user.getId();
        User userInDb = getUser(id);
        if(userInDb!=null){
            logger.info(">> UserService : User updated : Exiting update");
            return create(user);
        }
        else
        {
            logger.info(">> UserService : User with this id does not exist : Exiting update");
            return null;
        }
    }

    public User create(User user) {
        logger.info(" >> UserService : Entering create");

        User userToRet =  userRepository.save(user);
        logger.info(" << UserService : Exiting create");
        return userToRet;
    }
}