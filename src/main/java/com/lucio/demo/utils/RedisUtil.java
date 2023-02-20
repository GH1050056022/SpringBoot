package com.lucio.demo.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 保存值
     *
     * @param key
     * @param value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        ValueOperations<String, Object> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 设置有效时间
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public boolean deleteObject(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合 key
     * @param collection
     * @return
     */
    public long deleteObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 存入list
     * @param key
     * @param dataList
     * @return
     */
    public long setCacheList(String key, List<Object> dataList) {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 获取list
     * @param key
     * @return
     */
    public List<Object> getCacheList(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * 存入Set
     * @param key
     * @param dataSet
     * @return
     */
    public BoundSetOperations<String, Object> setCacheSet(final String key, final Set<Object> dataSet) {
        BoundSetOperations<String, Object> setOperation = redisTemplate.boundSetOps(key);
        Iterator<Object> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * 获取Set
     * @param key
     * @return
     */
    public Set<Object> getCacheSet(final String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 存入Map
     * @param key
     * @param dataMap
     */
    public void setCacheMap(final String key, final Map<String, Object> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取Map
     * @param key
     * @return
     */
    public Map<String, Object> getCacheMap(final String key) {
        Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
        Map<String, Object> map = new HashMap<>();
        entries.forEach((k, v) -> {
            map.put(k.toString(), v);
        });
        return map;
    }

    /**
     * 往Hash中存入数据
     * @param key
     * @param hKey
     * @param value
     */
    public void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     * @param key
     * @param hKey
     * @return
     */
    public Object getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, Object> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 获取多个Hash中的数据
     * @param key
     * @param hKeys
     * @return
     */
    public List<Object> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的基本对象列表
     * @param pattern
     * @return
     */
    public Collection<String> keys(final String pattern) {
        return redisTemplate.keys(pattern);
    }


}
