package com.helperok.chat.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.helperok.chat.SessionStore;
import org.springframework.stereotype.Component;

@Component
public class ClientConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        //获取登录用户id，并存入SessionStore
        long userId = Long.parseLong(socketIOClient.getHandshakeData().getSingleUrlParam("userId"));
        System.out.println("用户" + userId + "已连接");
        SessionStore.addClient(userId, socketIOClient.getSessionId());
    }
}
