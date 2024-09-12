package org.example.common.websocket;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/ws/{token}")
@Component
public class WebsocketServer {

    /**
     * 发送消息
     * @param accountName
     * @param jsonData
     */
    public static final void sendWebsocket(String accountName, String jsonData){
        //找到客户端
        Session session;
        int count = 3;
        //超时重连
        while(count-- > 0){
            session = WebsocketServer.clients.get(accountName);
            if(session != null){
                //说明已经拿到了,发送消息
                try {
                    System.out.println("webSocket 发送" + jsonData);
                    //消息需要是Json格式的
                    session.getBasicRemote().sendText(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }


    public static ConcurrentHashMap<String,Session> clients = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token){
        System.out.println("浏览器和服务器建立连接:"+token);
        //建立和浏览器的会话的映射关系
        clients.put(token,session);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:"+message);
    }

    @OnClose
    public void onClose(@PathParam("token") String token){
        System.out.println("浏览器和服务器断开连接:"+token);
        clients.remove(token);
    }

    @OnError
    public void onError(Throwable error) {
        error.printStackTrace();
    }

//    // 此为广播消息
//    public void sendAllMessage(String message) {
//        log.info("【websocket消息】广播消息:"+message);
//        for(WebSocket webSocket : webSockets) {
//            try {
//                if(webSocket.session.isOpen()) {
//                    webSocket.session.getAsyncRemote().sendText(message);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
    // 此为单点消息
//    public void sendOneMessage(String userId, String message) {
//        Session session = sessionPool.get(userId);
//        if (session != null && session.isOpen()) {
//            try {
//                log.info("【websocket消息】 单点消息:"+message);
//                session.getAsyncRemote().sendText(message);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // 此为单点消息(多人)
//    public void sendMoreMessage(String[] userIds, String message) {
//        for(String userId:userIds) {
//            Session session = sessionPool.get(userId);
//            if (session != null&&session.isOpen()) {
//                try {
//                    log.info("【websocket消息】 单点消息:"+message);
//                    session.getAsyncRemote().sendText(message);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
