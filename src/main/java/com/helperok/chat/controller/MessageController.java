package com.helperok.chat.controller;

import com.helperok.chat.entity.Message;
import com.helperok.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public void setRead(long senderId, long receiverId) {
        messageService.setMsgRead(senderId, receiverId);
    }

    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public List<Message> findHistory(long senderId, long receiverId, Long max_ts, Integer limit) {
        return messageService.findHistoryMessage(senderId, receiverId, max_ts, limit);
    }

    @RequestMapping(value = "/offline", method = RequestMethod.GET)
    public List<Message> findOffline(Long userId, Long min_ts) {
        return messageService.findOfflineMessages(userId, min_ts);
    }
}
