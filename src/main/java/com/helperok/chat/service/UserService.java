package com.helperok.chat.service;

import com.helperok.chat.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findUserById(Long id);

    //获取全部用户信息
    List<User> findAll();

    List<User> findUsersMeta(List<Long> userIds);
}
