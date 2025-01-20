package com.best.flyteng;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;
import com.best.flyteng.config.BestException;
import com.best.flyteng.entity.Personas;
import com.best.flyteng.entity.User;
import com.best.flyteng.mapper.PersonasMapper;
import com.best.flyteng.mapper.UserMapper;
import com.best.flyteng.utils.EmailUtils;
import com.best.flyteng.utils.Sm2Util;
import jakarta.annotation.Resource;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
@ComponentScan(basePackages = "com.best.flyteng")
class FlyTengApplicationTests {
  @Resource
  private UserMapper userMapper;
  @Resource
  private PersonasMapper personasMapper;
  @Resource
  private EmailUtils emailUtils;
  @Value("${sm.private.key}")
  private String smPrivateKey;
  @Value("${sm.public.key}")
  private String smPublicKey;
  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  @Test
  void contextLoads() throws BestException {

  }

}
