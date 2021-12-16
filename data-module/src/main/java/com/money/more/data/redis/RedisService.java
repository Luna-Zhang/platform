package com.money.more.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Describe：redis服务类
 * @Author：luna
 * @Date：2021/11/29:16:30
 */
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, Object> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    /**
     * 判断Redis中是否存在KEY
     *
     * @param key
     * @return
     */
    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public boolean deleteKey(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 设置缓存生命周期
     *
     * @param key
     * @param time
     * @param timeUnit
     */
    public void expireKey(String key, long time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    /**
     * 获取KEY的值
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return valueOperations.get(key);
    }

    /**
     * 获取KEY的值
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        Object obj = get(key);
        if (null == obj) {
            return null;
        }
        return obj.toString();
    }


    /**
     * 永久放入缓存
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        valueOperations.set(key, value);
    }

    /**
     * 放入缓存并设置时间(默认秒)
     *
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, Object value, long time) {
        if (time > 0) {
            valueOperations.set(key, value, time, TimeUnit.SECONDS);
        } else {
            set(key, value);
        }
    }

    /**
     * 向redis中存map
     *
     * @param key
     * @param map
     */
    public void setMap(String key, Map map) {
        hashOperations.putAll(key, map);
    }

    /**
     * 从redis中取map
     *
     * @param key
     * @return
     */
    public Map getMap(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 使用scan命令 查询某些前缀的key
     *
     * @param key
     * @return
     */
    public Set<String> scan(String key) {
        Set<String> execute = this.redisTemplate.execute(new RedisCallback<Set<String>>() {

            @Override
            public Set<String> doInRedis(RedisConnection connection) throws DataAccessException {

                Set<String> binaryKeys = new HashSet<>();

                Cursor<byte[]> cursor = connection.scan(new ScanOptions.ScanOptionsBuilder().match(key).count(1000).build());
                while (cursor.hasNext()) {
                    binaryKeys.add(new String(cursor.next()));
                }
                return binaryKeys;
            }
        });
        return execute;
    }

    /**
     * 使用scan命令 查询某些前缀的key 有多少个
     * 用来获取当前session数量,也就是在线用户
     *
     * @param key
     * @return
     */
    public Long scanSize(String key) {
        long dbSize = this.redisTemplate.execute(new RedisCallback<Long>() {

            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                long count = 0L;
                Cursor<byte[]> cursor = connection.scan(ScanOptions.scanOptions().match(key).count(1000).build());
                while (cursor.hasNext()) {
                    cursor.next();
                    count++;
                }
                return count;
            }
        });
        return dbSize;
    }


    public boolean lock(String key) {
        return this.lock(key, 60);
    }

    public boolean lock(String key, int seconds) {
        return this.lock(key, "1", seconds);
    }

    /**
     * 利用redis实现锁
     *
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public boolean lock(String key, String value, int seconds) {
        String oldValue = this.getString(key);
        if (oldValue != null && oldValue.equals(value)) {
            return false;
        }
        this.set(key, value, seconds);
        return true;
    }

    public boolean unLock(String key) {
        return this.deleteKey(key);
    }

    /**
     * 计数
     *
     * @param key
     */
    public void incrCount(String key) {
        String value = this.getString(key);
        int count = 0;
        if (value != null) {
            count = Integer.valueOf(value);
            count++;
            this.set(key, String.valueOf(count));
        } else {
            this.set(key, String.valueOf(count));
        }
    }


}
