package com.helperok.chat.service.impl;

import com.helperok.chat.dao.SessionDao;
import com.helperok.chat.entity.Session;
import com.helperok.chat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionDao sessionDao;

    @Override
    public List<Session> findSessionByUserId(Long userId) {
        return sessionDao.findSessionByUserId(userId);
    }

    @Override
    public Session findSessionByIds(Long userId, Long otherId) {
        return sessionDao.findSessionByIds(userId,otherId);
    }

    @Override
    public void deleteSession(Long userId, Long otherId) {
        Assert.notNull(userId);
        Assert.notNull(otherId);
        Session session=sessionDao.findSessionByIds(userId,otherId);
        if (userId.equals(session.getSenderId())){
            session.setIsSenderDeleted(true);
        }else if(userId.equals(session.getReceiverId())){
            session.setIsReceiverDeleted(true);
        }
        sessionDao.save(session);
    }
}
