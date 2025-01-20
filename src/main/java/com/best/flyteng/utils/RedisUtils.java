package com.best.flyteng.utils;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils<T> {
  @Resource
  private RedisTemplate<String, T> redisTemplate;

  public void set(String key, T value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public void set(String key, T value, long timeout, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  public boolean del(String key) {
    Boolean ret = redisTemplate.delete(key);
    return Boolean.TRUE.equals(ret);
  }

  public long del(Collection<String> keys) {
    Long ret = redisTemplate.delete(keys);
    return ret == null ? 0 : ret;
  }


  public T get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

}
