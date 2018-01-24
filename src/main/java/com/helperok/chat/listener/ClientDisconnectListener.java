package com.helperok.chat.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.helperok.chat.SessionStore;

public class ClientDisconnectListener implements DisconnectListener{
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        //获取连接用户Id,并从SessionStore中删除
        long userId=Long.parseLong(socketIOClient.getHandshakeData().getSingleUrlParam("userId"));
        SessionStore.delClient(userId);
    }
}
