package com.helperok.chat.service;

import com.helperok.chat.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        user.setNickname("赵云");
        System.out.println(userService.addUser(user));
    }

    @Test
    public void selectUser() {
        System.out.println(userService.findUserByNickname("赵云"));
    }
}