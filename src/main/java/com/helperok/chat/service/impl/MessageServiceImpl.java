package com.helperok.chat.service.impl;

import com.helperok.chat.dao.MessageDao;
import com.helperok.chat.dao.SessionDao;
import com.helperok.chat.entity.Message;
import com.helperok.chat.entity.Session;
import com.helperok.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private SessionDao sessionDao;

    @Override
    public Message findTopMessage(Long userId, Long otherId) {
        return messageDao.findTopMessage(userId,otherId);
    }

    @Override
    public List<Message> findHistoryMessage(long userId, long otherId, Long max_tx, int limit) {
        //max_tx long转Date,limit用PageRequest封装
        return messageDao.findHistoryByIds(userId,otherId,new Date(max_tx),new PageRequest(0,limit, Sort.Direction.DESC,"createTime"));
    }

    @Override
    public List<Message> findOfflineMessages(Long userId, Long min_ts) {
        //min_tx Long转Date
        return messageDao.findOfflineMessages(userId,new Date(min_ts));
    }

    @Override
    public void setMsgRead(Long userId, Long otherId) {
        messageDao.setReceiverMsgRead(userId,otherId);
    }

    @Override
    public int findUnReadNum(Long userId, Long otherId) {
        return  messageDao.countUnRead(userId,otherId);
    }

    @Override
    public Message createMessage(Message message) {
        Session session=sessionDao.findSessionByIds(message.getSenderId(),message.getReceiverId());
        if(session==null){
            //不存在会话则创建会话
            Session newSession=new Session();
            newSession.setSenderId(message.getSenderId());
            newSession.setReceiverId(message.getReceiverId());
            newSession.setCreateTime(new Date());
            sessionDao.save(newSession);
        }else{
            //已存在则更新聊天时间
            session.setIsReceiverDeleted(false);
            session.setIsSenderDeleted(false);
            session.setLastChatTime(new Date());
            sessionDao.save(session);
        }
        return messageDao.save(message);
    }
}
