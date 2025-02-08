package com.best.flyteng.config;

import com.best.flyteng.entity.EnvironmentProperty;
import com.best.flyteng.entity.TokenDTO;
import com.best.flyteng.utils.RedisUtils;
import com.best.flyteng.utils.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.ObjectUtils;

public class LogoutHandlerOverride implements LogoutHandler {
  private final RedisUtils<String> redisUtils;
  private final String smPrivateKey;

  LogoutHandlerOverride(EnvironmentProperty environmentProperty, RedisUtils<String> redisUtils) {
    this.smPrivateKey = environmentProperty.getPrivateKey();
    this.redisUtils = redisUtils;
  }

  @SneakyThrows
  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
    String header = request.getHeader("Authorization");
    if (header == null || !header.startsWith("Bearer ")) return;
    // 获取 JWT Token
    String token = header.replace("Bearer ", "");
    // 验证 JWT Token
    if (!token.isEmpty()) {
      TokenDTO value = TokenUtils.getValue(token, smPrivateKey);
      if (!ObjectUtils.isEmpty(value)) {
        redisUtils.del(String.valueOf(value.getUserId()));
      }
    }
  }
}
