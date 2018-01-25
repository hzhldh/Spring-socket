package com.helperok.chat.dao;

import com.helperok.chat.entity.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SessionDao extends PagingAndSortingRepository<Session, Long> {
    @Query("select s from Session s where (s.senderId=?1 and s.isSenderDeleted=0) or (s.receiverId=?1 and s.isReceiverDeleted=0) order by s.lastChatTime desc")
    List<Session> findSessionByUserId(Long userId);

    @Query("select s from Session s where (s.senderId=?1 and s.receiverId =?2) or (s.senderId=?2 and s.receiverId=?1)")
    Session findSessionByIds(Long userId, Long otherId);

}
