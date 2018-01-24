package com.helperok.chat.service;

import com.helperok.chat.entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    User findUserById(Long id);

    User findUserByNickname(String nickname);

    //获取全部用户信息
    List<User> findAll();

    List<User> findUsersMeta(List<Long> userIds);
}
