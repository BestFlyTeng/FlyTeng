package com.best.flyteng.config.chain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.best.flyteng.entity.EnvironmentProperty;
import com.best.flyteng.utils.RedisUtils;
import com.best.flyteng.utils.Sm2Util;
import com.best.flyteng.utils.TokenUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.HtmlSanitizer;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.io.IOException;
import java.util.Set;

/**
 * 请求加解密过滤器
 */
public class RequestHandlerFilter implements Filter {
  private final String smPrivateKey;
  private RedisUtils<String> redisUtils;

  public RequestHandlerFilter(EnvironmentProperty environmentProperty) {
    smPrivateKey = environmentProperty.getPrivateKey();
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  /**
   * 进行请求加密
   */
  @SneakyThrows
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
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

    // XSS 防范
    PolicyFactory factory = new HtmlPolicyBuilder()
            .disallowElements("script", "iframe")
            .toFactory();
    String s = factory.sanitize(body)
            .replace("&#34;", "\"")
            .replace("&#64;", "@");
    request = new BodyRequestWrapper((HttpServletRequest) request, s);
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {

  }
}