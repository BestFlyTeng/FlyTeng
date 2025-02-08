package com.best.flyteng.config;

import com.best.flyteng.entity.result.ResultData;
import com.best.flyteng.entity.result.ReturnCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResultData handleException(Exception e) {
    log.error("全局异常==>", e);
    return ResultData.fail(ReturnCode.RC500.getCode(), ReturnCode.RC500.getMessage());
  }

  @ExceptionHandler(DisabledException.class)
  public ResultData handleDisabledException(Exception e) {
    log.error("用户禁用异常==>", e);
    return ResultData.fail(ReturnCode.RC400.getCode(), "用户已禁用，请联系管理员");
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResultData handleAuthenticationException(AuthenticationException e) {
    log.error("用户认证异常==>", e);
    return ResultData.fail(ReturnCode.RC400.getCode(), "用户名或密码错误");
  }

  // 捕获所有 BestException 异常
  @ExceptionHandler(BestException.class)
  public ResultData handleBestException(BestException e, Model model) {
    log.error("Best异常==>{}", e.getMessage());
    return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class, HandlerMethodValidationException.class})
  public ResultData MethodArgumentNotValidExceptionHandler(Exception e) {
    log.error("参数校验异常==>{}", e.getMessage());
    return ResultData.fail(ReturnCode.RC400.getCode(), ReturnCode.RC400.getMessage());
  }
  @ExceptionHandler(NoResourceFoundException.class)
  public ResultData handleNoResourceFoundException(NoResourceFoundException e) {
    log.error("静态资源异常==>{}", e.getMessage());
    return ResultData.fail(ReturnCode.RC404.getCode(), ReturnCode.RC404.getMessage());
  }
}
