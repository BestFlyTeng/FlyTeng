package com.best.flyteng.entity.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ResultData {
  /**
   * 结果状态 ,具体状态码参见ResultData.java
   */
  private int status;
  private String message;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private Object data;
  private long timestamp;

  public ResultData() {
    this.timestamp = System.currentTimeMillis();
  }

  public static ResultData success(Object data) {
    ResultData resultData = new ResultData();
    resultData.setStatus(ReturnCode.RC200.getCode());
    resultData.setMessage(ReturnCode.RC200.getMessage());
    resultData.setData(data);
    return resultData;
  }

  public static ResultData fail(int code, String message) {
    ResultData resultData = new ResultData();
    resultData.setStatus(code);
    resultData.setMessage(message);
    return resultData;
  }

}