package com.best.flyteng.config.chain;

import com.alibaba.fastjson.JSONObject;
import com.best.flyteng.entity.result.ResultData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 响应加解密拦截器
 */
@Component
@RestControllerAdvice
public class ResponseHandler implements ResponseBodyAdvice<Object> {
  @Value("${sm.public.key}")
  private String publicKey;

  /**
   * 返回true，才会走beforeBodyWrite方法
   */
  @Override
  public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @SneakyThrows
  @Override
  public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse serverHttpResponse) {
    serverHttpResponse.getHeaders().setContentType(MediaType.APPLICATION_JSON);
    if (body instanceof String) {
      return JSONObject.toJSONString(ResultData.success(body));
    }
    if (body instanceof ResultData) {
      return body;
    }
    return ResultData.success(body);
    // 拿到响应的数据
//    String json = JSON.toJSONString(body);
//    // 进行加密
//    return Sm2Util.encrypt(publicKey, json);
  }
}