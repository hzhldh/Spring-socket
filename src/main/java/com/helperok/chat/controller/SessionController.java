package com.helperok.chat.controller;

import com.helperok.chat.entity.Message;
import com.helperok.chat.entity.Session;
import com.helperok.chat.service.MessageService;
import com.helperok.chat.service.SessionService;
import com.helperok.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    public List<Object> findSessions(long userId) {
        List<Session> sessionList=sessionService.findSessionByUserId(userId);

        //查询未读消息数
        List<Object> sessions = new ArrayList<>();
        for (Session session : sessionList) {
            //判断记录中聊天对象
            Long chatUserId=session.getSenderId().equals(userId)?session.getReceiverId():session.getSenderId();
            Map<String, Object> item = new HashMap<>();
            item.put("session", session);
            //查询最后一条消息
            item.put("lastMsg", messageService.findTopMessage(session.getSenderId(), session.getReceiverId()));
            //查询未读数
            item.put("unRead", messageService.findUnReadNum(chatUserId, userId));
            //查询用户信息
            item.put("chatUser",userService.findUserById(chatUserId));
            sessions.add(item);
        }

        return sessions;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteSession(long userId, long otherId) {
        sessionService.deleteSession(userId, otherId);
    }


}
