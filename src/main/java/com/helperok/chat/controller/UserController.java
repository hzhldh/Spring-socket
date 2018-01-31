package com.helperok.chat.controller;

import com.google.common.collect.Lists;
import com.helperok.chat.entity.User;
import com.helperok.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    /*
    获取指定用户信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") long id) {
        return userService.findUserById(id);
    }

    /*
    获取全部用户信息
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUserById() {
        return userService.findAll();
    }

    /*
    批量获取用户信息
     */
    @RequestMapping(value = "/meta", method = RequestMethod.GET)
    public List<User> getUserByIds(@RequestParam(value = "user_ids") Long[] userIds) {
        return userService.findUsersMeta(Lists.newArrayList(userIds));
    }

    /*
    用户登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public User login(String nickname){
        User user=userService.findUserByNickname(nickname);
        if(user!=null){
            return user;
        }else {
            User newUser=new User();
            newUser.setNickname(nickname);
            return userService.addUser(newUser);
        }
    }

}
