package com.helperok.chat.dao;

import com.helperok.chat.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserDao extends PagingAndSortingRepository<User,Long>{
    @Query("select u from User u")
    List<User> findAll();

    @Query("select u from User u where id in(?1)")
    List<User> findUserMeta(List<Long> userIds);
}
