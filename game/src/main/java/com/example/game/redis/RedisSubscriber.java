package com.example.game.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: zhangat
 * @Date: 2020-1-4 0004 14:20
 * @DESCIBE
 */
@Slf4j
@Component
public class RedisSubscriber extends MessageListenerAdapter {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(message);
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        String msg = (String) redisTemplate.getStringSerializer().deserialize(body);
        String topic = (String) redisTemplate.getStringSerializer().deserialize(channel);
         System.out.println("监听到topic为" + topic + "的消息：" + msg);
    }
}
