package com.example.game.redis;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhangat
 * @Date: 2019-8-12 0012 16:59
 * @DESCIBE
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate redisTemplate;

    private final long EXPIRE_TIME = 300L;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value, EXPIRE_TIME, TimeUnit.SECONDS);
    }

    public void set(String key, Object value, Long times) {
        redisTemplate.opsForValue().set(key, value, times, TimeUnit.SECONDS);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 返回key 的剩余过期时间（秒）
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 设置key过期日期
     *
     * @param key
     * @param date
     */
    public void setExireTime(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 取hash值
     *
     * @param key
     * @param field 属性字段
     * @return
     */
    public Optional<Object> hGet(String key, String field) {
        return Optional.ofNullable(redisTemplate.opsForHash().get(key, field));
    }

    /**
     * hash赋值
     *
     * @param key
     * @param field 属性字段
     * @param value 属性值
     */
    public void hset(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * hash长度
     *
     * @param key
     * @return
     */
    public long hsize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * hash赋值（原子操作）
     *
     * @param key
     * @param field
     * @param value
     */
    public void hcrby(String key, String field, Double value) {
        redisTemplate.opsForHash().increment(key, field, value);
    }


    public Set sets(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 分页查询
     *
     * @param key
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public <T> List<T> pageList(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 分页查询
     **/
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 消息订阅
     * @param channel
     * @param msg
     */
    public void sendMsg(String channel,String msg){
        redisTemplate.convertAndSend(channel, msg);
    }
}
