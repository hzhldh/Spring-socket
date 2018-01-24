package com.helperok.chat;

import com.corundumstudio.socketio.SocketIOClient;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionStore {
    //存放已连接的用户
    public static Map<Long, UUID> userStore = new ConcurrentHashMap<>();//ConcurrentMap接口代表一个Map,它可以处理并发访问

    public static void addClient(long userId, UUID uuid) {
        userStore.put(userId, uuid);
    }

    public static void delClient(long userId) {
        userStore.remove(userId);
    }

    public static SocketIOClient getClient(long userId) {
        if (userStore.containsKey(userId)) {
            UUID uuid = (UUID) userStore.get(userId);
            return IMServer.getClient(uuid);
        }
        return null;
    }
}
