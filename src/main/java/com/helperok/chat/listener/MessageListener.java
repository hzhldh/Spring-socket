package com.helperok.chat.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.helperok.chat.Message;
import com.helperok.chat.SessionStore;


public class MessageListener implements DataListener<Message> {

@Override
    public void onData(SocketIOClient socketIOClient, Message message, AckRequest ackRequest) throws Exception {
    //参考https://github.com/mrniko/netty-socketio-demo/blob/master/server/src/main/java/com/corundumstudio/socketio/demo/AckChatLauncher.java
        //保存
        //转发
        SocketIOClient receiverClient = SessionStore.getClient(message.getReceiverId());//获取消息接受者的客户端
        if (receiverClient == null) {
            System.out.println("用户"+message.getReceiverId()+"当前不在线");
        } else {
            //接收者收到消息的回调按需添加
            receiverClient.sendEvent("msg",message);//转发给接收者客户端
        }
        ackRequest.sendAckData(message);//反馈给发送者客户端
    }
}
