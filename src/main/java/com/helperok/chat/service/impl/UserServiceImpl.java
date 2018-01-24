package com.helperok.chat.service.impl;

import com.helperok.chat.dao.UserDao;
import com.helperok.chat.entity.User;
import com.helperok.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findOne(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findUsersMeta(List<Long> userIds) {
        return userDao.findUserMeta(userIds);
    }
}
