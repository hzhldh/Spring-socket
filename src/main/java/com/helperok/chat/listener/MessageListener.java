package com.helperok.chat.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.helperok.chat.entity.Message;
import com.helperok.chat.SessionStore;
import com.helperok.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener implements DataListener<Message> {
    @Autowired
    private MessageService messageService;

    @Override
    public void onData(SocketIOClient socketIOClient, Message message, AckRequest ackRequest) throws Exception {
        //参考https://github.com/mrniko/netty-socketio-demo/blob/master/server/src/main/java/com/corundumstudio/socketio/demo/AckChatLauncher.java

        try {
            //保存
            message.setHasRead(false);
            final Message newMessage =messageService.createMessage(message);
            //转发
            SocketIOClient receiverClient = SessionStore.getClient(message.getReceiverId());//获取消息接受者的客户端
            if (receiverClient == null) {
                System.out.println("用户" + message.getReceiverId() + "当前不在线");
            } else {
                //接收者收到消息的回调按需添加
                receiverClient.sendEvent("msg", message);//转发给接收者客户端
            }
            ackRequest.sendAckData(newMessage);//反馈给发送者客户端
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
