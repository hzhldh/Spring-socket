package com.helperok.chat;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.helperok.chat.listener.ClientConnectListener;
import com.helperok.chat.listener.ClientDisconnectListener;
import com.helperok.chat.listener.MessageListener;
import com.helperok.chat.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class IMServer {
    public static SocketIOServer server;
    @Autowired
    private ClientConnectListener clientConnectListener;//对应类加Component注解
    @Autowired
    private ClientDisconnectListener clientDisconnectListener;
    @Autowired
    private MessageListener messageListener;


//    public static void main(String[] args) {
//
//        final String MSG_EVENT = "msg";
//        Configuration config = new Configuration();
//        config.setHostname("192.168.1.176");
//        config.setPort(9092);
//        server = new SocketIOServer(config);
//
//        server.addConnectListener(new ClientConnectListener());//添加连接监听
//
//        server.addDisconnectListener(new ClientDisconnectListener());//添加断开连接监听
//
//        server.addEventListener(MSG_EVENT, MessageService.class, new MessageListener());//添加消息监听
//
//        server.start();
//
//    }

    public void start() {
        final String MSG_EVENT = "msg";
        Configuration config = new Configuration();
        config.setHostname("192.168.1.176");
        config.setPort(9092);
        server = new SocketIOServer(config);

        server.addConnectListener(clientConnectListener);//添加连接监听

        server.addDisconnectListener(clientDisconnectListener);//添加断开连接监听

        server.addEventListener(MSG_EVENT, Message.class,messageListener);//添加消息监听

        server.start();
    }

    public void stop() {
        server.stop();
    }

    public static SocketIOClient getClient(UUID uuid) {
        if (server == null) return null;
        return server.getClient(uuid);
    }
}
