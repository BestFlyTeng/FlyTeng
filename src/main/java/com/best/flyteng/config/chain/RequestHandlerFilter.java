package com.best.flyteng.config.chain;

import com.alibaba.fastjson.JSONObject;
import com.best.flyteng.entity.EnvironmentProperty;
import com.best.flyteng.utils.Sm2Util;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.io.IOException;

/**
 * 请求加解密过滤器
 */
public class RequestHandlerFilter implements Filter {
  private final String smPrivateKey;

  public RequestHandlerFilter(EnvironmentProperty environmentProperty) {
    smPrivateKey = environmentProperty.getPrivateKey();
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  /**
   * 进行请求加密
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    System.out.println(((HttpServletRequest) request).getMethod());
    // TODO: 分片解密文件
    if ("multipart/form-data".equals(request.getContentType())) {
      chain.doFilter(request, response);
      return;
    }
    // 拿到加密串
    String data = new RequestWrapper((HttpServletRequest) request).getBody();
    if (data.isEmpty()) {
      chain.doFilter(request, response);
      return;
    }
    data = JSONObject.parseObject(data).get("data").toString();
    // 解析
    String body = Sm2Util.decrypt(smPrivateKey, data);
    request = new BodyRequestWrapper((HttpServletRequest) request, body);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}