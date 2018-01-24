package com.helperok.chat.service;

import com.helperok.chat.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:application-context.xml"
})

public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setNickname("!");
        user.setAvatar("122");
        userService.addUser(user);
    }
}