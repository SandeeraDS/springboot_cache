package com.ds.springbootredis;

import com.ds.springbootredis.bean.User;
import com.ds.springbootredis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableCaching
public class SpringbootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRedisApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initUsers() {
        List<User> users = Stream.of(
                new User(0, "userName0", "userName0@gmail.com"),
                new User(0, "userName1",  "userName1@gmail.com"),
                new User(0, "userName2",  "userName2@gmail.com"),
                new User(0, "userName3",  "userName3@gmail.com"),
                new User(0, "userName4",  "userName4@gmail.com")
        ).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

}
