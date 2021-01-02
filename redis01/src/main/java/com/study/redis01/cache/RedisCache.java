package com.study.redis01.cache;

import com.study.redis01.util.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

public class RedisCache implements Cache {

    // 当前放入缓存的mapper的namespace
    private final String id;

    public RedisCache(String id) {
        System.out.println("id:=================================>" + id);
        this.id = id;
    }

    // 返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    // 缓存放置
    @Override
    public void putObject(Object o, Object o1) {
        System.out.println("o:===================>" + o.toString());
        System.out.println("o1:===================>" + o1.toString());

        // 使用redishash类型作为缓存存储模型
        getRedisTemplate().opsForHash().put(id, getKeyToMD5(o.toString()), o1);
    }

    // 缓存获取
    @Override
    public Object getObject(Object o) {
        System.out.println("o:===================>" + o.toString());
        // 根据key 从redis的hash类型中获取数据
        return getRedisTemplate().opsForHash().get(id, getKeyToMD5(o.toString()));
    }

    @Override
    public Object removeObject(Object o) {
        System.out.println("根据指定key删除缓存");
        return null;
    }

    @Override
    public void clear() {
        System.out.println("清空缓存！！");
        // 清空缓存
        getRedisTemplate().delete(id);
    }

    // 用来计算缓存数量
    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    private RedisTemplate getRedisTemplate(){
        // 通过application工具获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    private String getKeyToMD5(String key){
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

}
