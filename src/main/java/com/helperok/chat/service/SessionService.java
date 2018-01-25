package com.helperok.chat.service;

import com.helperok.chat.entity.Session;

import java.util.List;

public interface SessionService {
    /*
    获取用户的会话列表
     */
    List<Session> findSessionByUserId(Long userId);

    /*
    获取两个用户之间的会话
     */
    Session findSessionByIds(Long userId,Long otherId);

    /*
    删除会话
     */
    void deleteSession(Long userId,Long otherId);
}
