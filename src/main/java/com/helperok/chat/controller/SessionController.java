package com.helperok.chat.controller;

import com.helperok.chat.entity.Session;
import com.helperok.chat.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/session")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "/recent", method = RequestMethod.GET)
    public List<Session> findSessions(long userId) {

        //循环查询最后一条消息

        //查询未读消息数

        return sessionService.findSessionByUserId(userId);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteSession(long userId, long otherId) {
        sessionService.deleteSession(userId, otherId);
    }


}
