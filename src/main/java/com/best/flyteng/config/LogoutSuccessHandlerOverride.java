package com.best.flyteng.config;

import com.best.flyteng.entity.result.ResultData;
import com.best.flyteng.entity.result.ReturnCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LogoutSuccessHandlerOverride implements LogoutSuccessHandler {
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.setCharacterEncoding("utf-8");
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    ObjectMapper objectMapper = new ObjectMapper();
    HashMap<String, String> map = new HashMap<>();
    map.put("tips", "退出成功");
    String resBody = objectMapper.writeValueAsString(ResultData.success(map));
    PrintWriter printWriter = response.getWriter();
    printWriter.print(resBody);
    printWriter.flush();
    printWriter.close();
  }
}
