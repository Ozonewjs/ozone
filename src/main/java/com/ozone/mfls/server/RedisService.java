package com.ozone.mfls.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisService
 * @Author Ozone
 * @Description redis服务类
 * @Date 2019/6/4 15:35
 * @Version 1.0
 **/
@Service
public class RedisService {
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public RedisService(RedisTemplate<String,Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    /**
     * @Author Ozone
     * @Description 写入缓存
     * @Date 2019/5/31 14:46
     * @Param [key, value]
     * @return boolean
     **/
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @Author Ozone
     * @Description 写入缓存设置时效时间
     * @Date 2019/5/31 14:46
     * @Param [key, value, expireTime]
     * @return boolean
     **/
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @Author Ozone
     * @Description 写入缓存设置时效时间
     * @Date 2019/5/31 14:46
     * @Param [key, expireTime]
     * @return boolean
     **/
    public boolean set(final String key,  Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
//            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * @Author Ozone
     * @Description 批量删除对应的value
     * @Date 2019/5/31 14:47
     * @Param [keys]
     * @return void
     **/
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * @Author Ozone
     * @Description 批量删除key
     * @Date 2019/5/31 14:47
     * @Param [pattern]
     * @return void
     **/
    public void removePattern(final String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys!=null && keys.size() > 0){
            redisTemplate.delete(keys);
        }

    }
    /**
     * @Author Ozone
     * @Description 删除对应的value
     * @Date 2019/5/31 14:48
     * @Param [key]
     * @return void
     **/
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * @Author Ozone
     * @Description 判断缓存中是否有对应的value
     * @Date 2019/5/31 14:48
     * @Param [key]
     * @return boolean
     **/
    public boolean exists(final String key) {
        boolean res = false;
        if (key!=null) {
            res = redisTemplate.hasKey(key);
            return res;
        }
        return res;
    }
    /**
     * @Author Ozone
     * @Description 读取缓存
     * @Date 2019/5/31 10:24
     * @Param [key]
     * @return java.lang.Object
     **/
    public Object get(final String key) {
        Object result = null;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }
    /**
     * @Author Ozone
     * @Description * 哈希 添加
     * @Date 2019/5/31 10:24
     * @Param [key, hashKey, value]
     * @return void
     **/

    public void hmSet(String key, Object hashKey, Object value){
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * 哈希获取数据
     * @param key
     * @param hashKey
     * @return
     */
    public Object hmGet(String key, Object hashKey){
        HashOperations<String, Object, Object>  hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * 列表添加
     * @param k
     * @param v
     */
    public void lPush(String k,Object v){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * 列表获取
     * @param k
     * @param l
     * @param l1
     * @return
     */
    public List<Object> lRange(String k, long l, long l1){
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * 集合添加
     * @param key
     * @param value
     */
    public void add(String key,Object value){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * 集合获取
     * @param key
     * @return
     */
    public Set<Object> setMembers(String key){
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加
     * @param key
     * @param value
     * @param scoure
     */
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key,value,scoure);
    }

    /**
     * 有序集合获取
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    public Set<Object> rangeByScore(String key,double scoure,double scoure1){
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

}
