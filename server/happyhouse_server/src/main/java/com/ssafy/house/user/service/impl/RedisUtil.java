package com.ssafy.house.user.service.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {
  @Autowired
  StringRedisTemplate redisTemplate;
  // key를 통해 value 리턴
  public String getData(String key) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    return valueOperations.get(key);
  }
    
  public void setData(String key, String value) {
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    valueOperations.set(key, value);
  }
    
  // 유효 시간 동안 (key, value) 저장
  public void setDataExpire(String key, String value, long duration) {
    System.out.println(key+" "+value);
    ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
    Duration expireDuration = Duration.ofSeconds(duration);
    valueOperations.set(key, value, expireDuration);
  }
    
  // 삭제
  public void deleteData(String key) {
    redisTemplate.delete(key);
  }
}
