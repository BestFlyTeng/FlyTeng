package com.best.flyteng.config;

import com.best.flyteng.entity.result.ResultData;
import com.best.flyteng.entity.result.ReturnCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json;charset=utf-8");
    response.getWriter().write(new ObjectMapper().writeValueAsString(ResultData.fail(ReturnCode.RC401.getCode(), ReturnCode.RC401.getMessage())));
  }
}
