package com.example.game.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: zhangat
 * @Date: 2020-1-3 0003 14:11
 * @DESCIBE
 */
@Slf4j
@ServerEndpoint("/chat/{uid}")
@Component
public class WebSocketServer {

    private static AtomicInteger onLine=new AtomicInteger(0);

    private Session session;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    /**
     * 接收id
     */
    private String uid;


    @OnOpen
    public void open(Session session,@PathParam("uid")String uid){
        this.session=session;
        this.uid=uid;
        webSocketSet.add(this);
        onLine.incrementAndGet();
        log.info("用户"+uid+"上线了，当前在线人数为："+onLine.get());
        try {
            sendMsg("链接成功!");
        } catch (IOException e) {
            log.error("链接异常:"+e.getMessage());
        }
    }

    @OnClose
    public void close(){
        webSocketSet.remove(this);
        onLine.decrementAndGet();
        log.info("用户"+this.uid+"下线了");
    }

    /**
     * 实现服务器主动推送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    @OnMessage
    public void onMessage(String msg,Session session){
        log.info("收到用户"+uid+"的信息："+msg);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public static int onLineSize(){
        return onLine.get();
    }
}
