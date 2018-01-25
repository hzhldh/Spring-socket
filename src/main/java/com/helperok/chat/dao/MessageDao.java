package com.helperok.chat.dao;

import com.helperok.chat.entity.Message;
import com.helperok.chat.entity.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface MessageDao extends PagingAndSortingRepository<Message,Long>{
    @Query("select m from Message m where ((m.senderId=?1 and m.receiverId =?2) or (m.senderId=?2 and m.receiverId=?1)) and m.createTime>?3 order by m.createTime desc")
    List<Message> findHistoryByIds(long userId, long otherId, Date max_ts, Pageable page);

    @Query(value = "select m.* from chat_message m where (m.sender_id=?1 and m.receiver_id =?2) or (m.sender_id=?2 and m.receiver_id=?1) order by m.create_time desc LIMIT 1",nativeQuery = true)
    Message findTopMessage(Long userId, Long otherId);

    @Query("select m from  Message m where (m.senderId=?1 or m.receiverId=?1) and m.createTime>?2 order by m.createTime desc")
    List<Message> findOfflineMessages(Long userId, Date minTs);

    @Query("update Message m set m.hasRead = 1 where m.senderId = ?1 and m.receiverId = ?2 and m.hasRead = 0")
    @Modifying
    int setReceiverMsgRead(Long senderId, Long receiverId);

    @Query("select count(m) from Message m where m.senderId=?1 and m.receiverId=?2 and m.hasRead=0")
    int countUnRead(Long senderId, Long receiverId);

}
