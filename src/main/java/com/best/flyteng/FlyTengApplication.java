package com.best.flyteng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class FlyTengApplication {

  public static void main(String[] args) {
    ConfigurableApplicationContext run = SpringApplication.run(FlyTengApplication.class, args);
    System.out.println("✧(◍˃̶ᗜ˂̶◍)✩ 启动成功 ✧(◍˃̶ᗜ˂̶◍)✩");
  }

}
