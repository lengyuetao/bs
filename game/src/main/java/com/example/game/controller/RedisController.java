package com.example.game.controller;

import com.example.game.redis.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: zhangat
 * @Date: 2020-1-4 0004 14:22
 * @DESCIBE
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class RedisController {

    @Resource
    private RedisUtils redisUtils;

    @RequestMapping("/msg")
    public String sendMsg(String channel,String msg){
        redisUtils.sendMsg(channel,msg);
        return "success";
    }
}
