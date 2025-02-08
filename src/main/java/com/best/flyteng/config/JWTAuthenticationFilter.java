package com.best.flyteng.config;

import com.best.flyteng.entity.EnvironmentProperty;
import com.best.flyteng.entity.TokenDTO;
import com.best.flyteng.utils.RedisUtils;
import com.best.flyteng.utils.TokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
  private String smPrivateKey;
  private RedisUtils<String> redisUtils;

  JWTAuthenticationFilter(AuthenticationManager authenticationManager, EnvironmentProperty environmentProperty, RedisUtils<String> redisUtils) {
    super(authenticationManager);
    this.smPrivateKey = environmentProperty.getPrivateKey();
    this.redisUtils = redisUtils;
  }


  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @SneakyThrows
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    // 从请求头获取 Authorization 信息
    String header = request.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      chain.doFilter(request, response);  // 如果没有 Bearer Token，继续过滤器链
      return;
    }

    // 获取 JWT Token
    String token = header.replace("Bearer ", "");
    // 验证 JWT Token
    if (!token.isEmpty()) {
      TokenDTO value = TokenUtils.getValue(token, smPrivateKey);
      if (ObjectUtils.isEmpty(value)) {
        chain.doFilter(request, response);
        return;
      }
      String userId = String.valueOf(value.getUserId());
      String s = redisUtils.get(userId);
      if (!ObjectUtils.isEmpty(s)) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        redisUtils.set(userId, s, 1, TimeUnit.HOURS);
      }
    }


    // 继续请求
    chain.doFilter(request, response);
  }
}
