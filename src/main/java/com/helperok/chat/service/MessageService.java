package com.helperok.chat.service;

import com.helperok.chat.entity.Message;

import java.util.List;

public interface MessageService {
    /*
    获取两个ID最新的消息记录
     */
    Message findTopMessage(Long userId, Long otherId);

    /*
    获取两个id之间的历史消息
    * @param id1    id1
     * @param id2    id2
     * @param max_ts 在此时间之前的消息
     * @param limit  消息数
     */
    List<Message> findHistoryMessage(long userId, long otherId, Long max_tx, int limit);

    /*
    获取某用户的离线消息
     */
    List<Message> findOfflineMessages(Long userId, Long min_ts);

    /*
    设置接收者消息已读
     */
    void setMsgRead(Long userId,Long otherId);

    /*
    查询未读消息数
     */
    int findUnReadNum(Long userId,Long otherId);


    Message createMessage(Message message);

}
