package com.helperok.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "chat_session")
public class Session {
    private Long id;
    private Long senderId;
    private Long receiverId;
    @JsonIgnore
    private Boolean isSenderDeleted;
    @JsonIgnore
    private Boolean isReceiverDeleted;
    private Date createTime;
    private Date lastChatTime;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public Boolean getIsSenderDeleted() {
        return isSenderDeleted;
    }

    public void setIsSenderDeleted(Boolean senderDeleted) {
        isSenderDeleted = senderDeleted;
    }

    public Boolean getIsReceiverDeleted() {
        return isReceiverDeleted;
    }

    public void setIsReceiverDeleted(Boolean receiverDeleted) {
        isReceiverDeleted = receiverDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastChatTime() {
        return lastChatTime;
    }

    public void setLastChatTime(Date lastChatTime) {
        this.lastChatTime = lastChatTime;
    }
}
