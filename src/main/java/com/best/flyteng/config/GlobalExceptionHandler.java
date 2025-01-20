package com.best.flyteng.config;

import com.best.flyteng.entity.result.ResultData;
import com.best.flyteng.entity.result.ReturnCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(Exception.class)
  public ResultData<String> handleException(Exception e) throws JsonProcessingException {
    log.error("全局异常==>", e);
    return ResultData.fail(ReturnCode.RC500.getCode(), ReturnCode.RC500.getMessage());
  }

  // 捕获所有 BestException 异常
  @ExceptionHandler(BestException.class)
  public ResultData<String> handleBestException(BestException e, Model model) {
    log.error("Best异常==>{}", e.getMessage());
    return ResultData.fail(ReturnCode.RC500.getCode(), e.getMessage());
  }

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class, HandlerMethodValidationException.class})
  public ResultData<String> MethodArgumentNotValidExceptionHandler(Exception e) {
    log.error("参数校验异常==>{}", e.getMessage());
    return ResultData.fail(ReturnCode.RC400.getCode(), ReturnCode.RC400.getMessage());
  }
}
